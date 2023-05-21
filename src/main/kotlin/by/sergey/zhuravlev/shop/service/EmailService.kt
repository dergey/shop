package by.sergey.zhuravlev.shop.service

import by.sergey.zhuravlev.shop.config.property.FrontendMailProperties
import by.sergey.zhuravlev.shop.config.property.MailProperties
import freemarker.template.Configuration
import org.slf4j.LoggerFactory
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import java.io.StringWriter
import java.util.*
import javax.mail.internet.MimeMessage

@Component
class EmailService(
  private val configuration: Configuration,
  private val javaMailSender: JavaMailSender,
  private val mailSubjectMessageSource: ResourceBundleMessageSource,
  private val mailProperties: MailProperties,
  private val frontendMailProperties: FrontendMailProperties,
) {

  @Async
  fun sendTemplate(recipient: String, subjectKey: String, template: String, rootObject: Any) {
    val mimeMessage: MimeMessage = javaMailSender.createMimeMessage()
    try {
      val mimeMessageHelper = MimeMessageHelper(mimeMessage, false)
      mimeMessageHelper.setFrom(mailProperties.sender)
      mimeMessageHelper.setTo(recipient)
      mimeMessageHelper.setSubject(mailSubjectMessageSource.getMessage(subjectKey, null, Locale.ENGLISH))
      mimeMessageHelper.setText(getEmailContent(template, rootObject), true)
      javaMailSender.send(mimeMessage)
    } catch (e: Exception) {
      log.warn("Email send fail: ", e)
    }
  }

  private fun getEmailContent(template: String, rootObject: Any): String {
    val stringWriter = StringWriter()
    val model = ModelMap()
    model.addAttribute("root", rootObject)
    model.addAttribute("frontendContext", frontendMailProperties)
    configuration.getTemplate(template).process(model, stringWriter)
    return stringWriter.buffer.toString()
  }

  companion object {
    private val log = LoggerFactory.getLogger(EmailService::class.java)
  }
}