package com.bjoggis.openai.function;

import com.bjoggis.openai.entity.Message;
import com.bjoggis.openai.entity.ThreadChannel;
import com.bjoggis.openai.function.SaveMessageFunction.SaveMessageOptions;
import com.bjoggis.openai.repository.MessageRepository;
import com.bjoggis.openai.type.Sender;
import java.time.LocalDateTime;
import java.util.function.Consumer;
import org.springframework.stereotype.Component;

@Component
public class SaveMessageFunction implements Consumer<SaveMessageOptions> {

  private final MessageRepository messageRepository;

  public SaveMessageFunction(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  @Override
  public void accept(SaveMessageOptions options) {

    Message entityMessage = new Message();
    entityMessage.setMessageId(options.messageId());
    entityMessage.setMessage(options.message());
    entityMessage.setCreated(LocalDateTime.now());
    entityMessage.setSender(options.isBot() ? Sender.ASSISTANT : Sender.USER);
    entityMessage.setThreadChannel(options.threadChannel());

    messageRepository.save(entityMessage);
  }

  public record SaveMessageOptions(String messageId, String message, Boolean isBot,
                                   ThreadChannel threadChannel) {

  }
}
