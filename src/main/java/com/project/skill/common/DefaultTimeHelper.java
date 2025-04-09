package com.project.skill.common;

import org.springframework.stereotype.Service;

@Service
class DefaultTimeHelper implements TimeHelper{
    @Override
    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
