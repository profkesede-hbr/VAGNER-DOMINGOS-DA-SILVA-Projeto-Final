package br.com.docemed.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
@Slf4j
public class RealtimeNotificationService {

    // Emitters globais (Médicos, Telão e Pacientes)
    private final List<SseEmitter> globalEmitters = new CopyOnWriteArrayList<>();
    
    // Emitters específicos por Paciente ID
    private final Map<Long, List<SseEmitter>> pacienteEmitters = new ConcurrentHashMap<>();

    public SseEmitter subscribeGlobal() {
        SseEmitter emitter = new SseEmitter(0L); // Sem timeout automático
        globalEmitters.add(emitter);

        emitter.onCompletion(() -> globalEmitters.remove(emitter));
        emitter.onTimeout(() -> globalEmitters.remove(emitter));
        emitter.onError(e -> globalEmitters.remove(emitter));

        try {
            emitter.send(SseEmitter.event().name("CONNECT").data(Map.of("message", "Conectado ao tempo real Doc-eMed")));
        } catch (IOException e) {
            globalEmitters.remove(emitter);
        }

        return emitter;
    }

    public SseEmitter subscribePaciente(Long pacienteId) {
        SseEmitter emitter = new SseEmitter(0L);
        pacienteEmitters.computeIfAbsent(pacienteId, k -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removePacienteEmitter(pacienteId, emitter));
        emitter.onTimeout(() -> removePacienteEmitter(pacienteId, emitter));
        emitter.onError(e -> removePacienteEmitter(pacienteId, emitter));

        try {
            emitter.send(SseEmitter.event().name("CONNECT").data(Map.of("pacienteId", pacienteId, "status", "Conectado")));
        } catch (IOException e) {
            removePacienteEmitter(pacienteId, emitter);
        }

        return emitter;
    }

    private void removePacienteEmitter(Long pacienteId, SseEmitter emitter) {
        List<SseEmitter> list = pacienteEmitters.get(pacienteId);
        if (list != null) {
            list.remove(emitter);
            if (list.isEmpty()) {
                pacienteEmitters.remove(pacienteId);
            }
        }
    }

    public void emitGlobalEvent(String eventName, Object data) {
        for (SseEmitter emitter : globalEmitters) {
            try {
                emitter.send(SseEmitter.event().name(eventName).data(data));
            } catch (Exception e) {
                globalEmitters.remove(emitter);
            }
        }
    }

    public void emitPacienteEvent(Long pacienteId, String eventName, Object data) {
        List<SseEmitter> list = pacienteEmitters.get(pacienteId);
        if (list != null) {
            for (SseEmitter emitter : list) {
                try {
                    emitter.send(SseEmitter.event().name(eventName).data(data));
                } catch (Exception e) {
                    list.remove(emitter);
                }
            }
        }
        // Notifica também canais globais (Médico / Telão)
        emitGlobalEvent(eventName, data);
    }
}
