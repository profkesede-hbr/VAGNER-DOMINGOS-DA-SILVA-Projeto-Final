package br.com.docemed.repository;

import br.com.docemed.model.MensagemChat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensagemChatRepository extends JpaRepository<MensagemChat, Long> {

    @Query("SELECT m FROM MensagemChat m WHERE " +
           "(LOWER(m.remetenteLogin) = LOWER(:u1) AND LOWER(m.destinatarioLogin) = LOWER(:u2)) OR " +
           "(LOWER(m.remetenteLogin) = LOWER(:u2) AND LOWER(m.destinatarioLogin) = LOWER(:u1)) " +
           "ORDER BY m.dataEnvio ASC")
    List<MensagemChat> findConversaPrivada(@Param("u1") String u1, @Param("u2") String u2);

    List<MensagemChat> findTop100ByOrderByDataEnvioDesc();
}
