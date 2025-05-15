package com.legalDigital.education.domain.dto.test;

import java.util.List;

public record TestAnswers(Long questionId, List<Long> answerIds) {
}
