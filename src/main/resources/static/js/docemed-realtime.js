/**
 * Doc-eMed — Cliente Realtime SSE & Notificações Interativas
 */

class DocEMedRealtime {
    constructor(options = {}) {
        this.pacienteId = options.pacienteId || null;
        this.isMedico = options.isMedico || false;
        this.isTelao = options.isTelao || false;
        this.onEvent = options.onEvent || (() => {});
        this.eventSource = null;

        this.initAudio();
        this.connect();
    }

    initAudio() {
        try {
            const AudioContext = window.AudioContext || window.webkitAudioContext;
            this.audioCtx = new AudioContext();
        } catch (e) {
            console.warn('Web Audio API não suportada neste navegador.', e);
        }
    }

    playChime() {
        if (!this.audioCtx) return;
        if (this.audioCtx.state === 'suspended') {
            this.audioCtx.resume();
        }

        const now = this.audioCtx.currentTime;
        const osc1 = this.audioCtx.createOscillator();
        const osc2 = this.audioCtx.createOscillator();
        const gainNode = this.audioCtx.createGain();

        // Tom agradável de sino hospitalar / clínica (Frequências: 587.33Hz (D5) -> 880Hz (A5))
        osc1.type = 'sine';
        osc1.frequency.setValueAtTime(587.33, now);
        osc1.frequency.exponentialRampToValueAtTime(880, now + 0.3);

        osc2.type = 'triangle';
        osc2.frequency.setValueAtTime(293.66, now);

        gainNode.gain.setValueAtTime(0.3, now);
        gainNode.gain.exponentialRampToValueAtTime(0.001, now + 1.2);

        osc1.connect(gainNode);
        osc2.connect(gainNode);
        gainNode.connect(this.audioCtx.destination);

        osc1.start(now);
        osc2.start(now);
        osc1.stop(now + 1.2);
        osc2.stop(now + 1.2);
    }

    connect() {
        const url = this.pacienteId 
            ? `/api/fila/realtime/paciente/${this.pacienteId}/stream`
            : `/api/fila/realtime/stream`;

        console.log(`[Doc-eMed Realtime] Conectando ao fluxo SSE: ${url}`);
        this.eventSource = new EventSource(url);

        this.eventSource.addEventListener('CONNECT', (e) => {
            console.log('[Doc-eMed Realtime] Conexão ativa:', e.data);
        });

        const events = [
            'NOVO_AGENDAMENTO',
            'AGENDAMENTO_CONFIRMADO',
            'REAGENDAMENTO_SOLICITADO',
            'REAGENDAMENTO_ACEITO',
            'PACIENTE_CHAMADO',
            'FILA_ATUALIZADA',
            'STATUS_FILA_ALTERADO'
        ];

        events.forEach(evt => {
            this.eventSource.addEventListener(evt, (e) => {
                const data = JSON.parse(e.data);
                console.log(`[Doc-eMed Evento] ${evt}:`, data);
                
                if (evt === 'PACIENTE_CHAMADO') {
                    this.playChime();
                }

                this.showToast(evt, data);
                this.onEvent(evt, data);
            });
        });

        this.eventSource.onerror = () => {
            console.warn('[Doc-eMed Realtime] Conexão perdida. Tentando reconectar automaticamente...');
        };
    }

    showToast(eventType, data) {
        let title = 'Doc-eMed';
        let message = '';
        let icon = '🔔';

        switch (eventType) {
            case 'NOVO_AGENDAMENTO':
                if (!this.isMedico) return;
                title = 'Novo Agendamento Recebido';
                message = `Paciente ${data.pacienteNome} solicitou consulta para ${data.dataHoraFormatada}.`;
                icon = '📅';
                break;
            case 'AGENDAMENTO_CONFIRMADO':
                title = 'Consulta Confirmada!';
                message = data.mensagem;
                icon = '✅';
                break;
            case 'REAGENDAMENTO_SOLICITADO':
                title = 'Aviso de Reagendamento';
                message = `O médico sugeriu a data: ${data.novaDataHoraFormatada}`;
                icon = '⚠️';
                break;
            case 'PACIENTE_CHAMADO':
                title = 'CHAMADA DE ATENDIMENTO';
                message = `${data.pacienteNome} -> ${data.sala} (${data.medicoNome})`;
                icon = '📢';
                break;
            case 'REAGENDAMENTO_ACEITO':
                if (!this.isMedico) return;
                title = 'Paciente Confirmou Reagendamento';
                message = data.mensagem;
                icon = '🤝';
                break;
            default:
                return;
        }

        this.createToastElement(title, message, icon);
    }

    createToastElement(title, message, icon) {
        let container = document.getElementById('toastContainer');
        if (!container) {
            container = document.createElement('div');
            container.id = 'toastContainer';
            container.className = 'toast-container';
            document.body.appendChild(container);
        }

        const toast = document.createElement('div');
        toast.className = 'toast';
        toast.innerHTML = `
            <div style="font-size: 1.5rem;">${icon}</div>
            <div style="flex: 1;">
                <div style="font-weight: 700; font-size: 0.95rem; margin-bottom: 2px;">${title}</div>
                <div style="font-size: 0.85rem; color: #cbd5e1;">${message}</div>
            </div>
        `;

        container.appendChild(toast);

        setTimeout(() => {
            toast.style.opacity = '0';
            toast.style.transform = 'translateX(100%)';
            toast.style.transition = 'all 0.3s ease';
            setTimeout(() => toast.remove(), 300);
        }, 5000);
    }
}
