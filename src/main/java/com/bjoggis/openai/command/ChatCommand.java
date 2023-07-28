package com.bjoggis.openai.command;

import jakarta.validation.constraints.NotNull;

public record ChatCommand(@NotNull String message, @NotNull String threadId) {

}