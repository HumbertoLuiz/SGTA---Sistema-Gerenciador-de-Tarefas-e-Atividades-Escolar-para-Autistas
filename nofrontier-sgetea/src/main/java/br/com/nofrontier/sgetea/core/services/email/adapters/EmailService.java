package br.com.nofrontier.sgetea.core.services.email.adapters;

import br.com.nofrontier.sgetea.core.services.email.dtos.EmailParams;

public interface EmailService {

    void sendMailTemplateHtml(EmailParams params);
}
