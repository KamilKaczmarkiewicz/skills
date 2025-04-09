package com.project.skill;

import com.project.skill.common.TimeHelper;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class TestTimeHelper implements TimeHelper {
    @Override
    public void sleep(long millis) {

    }
}
