package com.project.skill.task;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;


class TaskProcessorTest {

    private final TaskCacheService taskCacheService = mock(TaskCacheService.class);
    private final ComparableObjectRepository comparableObjectRepository = mock(ComparableObjectRepository.class);
    private final TaskProcessor calculator = new TaskProcessor(taskCacheService, comparableObjectRepository);

    static Stream<Arguments> similarityData() {
        return Stream.of(
                Arguments.of("ABCD", "BCD", 0.75),
                Arguments.of("ABCD", "BWD", 0.5),
                Arguments.of("ABCDEFG", "CFG", 0.4286),
                Arguments.of("ABCABC", "ABC", 0.5),
                Arguments.of("ABCDEFGH", "TDD", 0.125)
        );
    }

    @ParameterizedTest
    @MethodSource("similarityData")
    void testCalculateSimilarity(String source, String target, double expected) {
        double similarity = calculator.calculateSimilarity(source, target);
        assertEquals(expected, similarity, 0.0001);
    }

    static Stream<Arguments> classificationData() {
        return Stream.of(
                Arguments.of(1.0, Classification.HIGH),
                Arguments.of(0.91, Classification.HIGH),
                Arguments.of(0.9, Classification.MEDIUM),
                Arguments.of(0.7, Classification.MEDIUM),
                Arguments.of(0.4, Classification.MEDIUM),
                Arguments.of(0.3999, Classification.LOW),
                Arguments.of(0.1, Classification.LOW),
                Arguments.of(0.0, Classification.LOW)
        );
    }

    @ParameterizedTest
    @MethodSource("classificationData")
    void testGetClassificationBySimilarity(double similarity, Classification expected) {
        Classification classification = calculator.getClassificationBySimilarity(similarity);
        assertEquals(expected, classification);
    }
}