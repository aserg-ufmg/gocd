/*************************GO-LICENSE-START*********************************
 * Copyright 2014 ThoughtWorks, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *************************GO-LICENSE-END***********************************/

package com.thoughtworks.go.utils;

import movedclasses.RenameClass4;

import org.hamcrest.Matcher;
import org.hamcrest.Description;
import org.apache.log4j.Level;
import org.hamcrest.TypeSafeMatcher;

public class LogFixtureMatcher {
    public static Matcher<RenameClass4> containsLog(final Level level, final String message) {
        return new TypeSafeMatcher<RenameClass4>() {
            private RenameClass4 logFixture;

            @Override public boolean matchesSafely(RenameClass4 item) {
                logFixture = item;
                return logFixture.contains(level, message);
            }

            public void describeTo(Description description) {
                description.appendText(
                        String.format("Expected to contains log [%s - %s], actual logs are: \n%s", level,
                                message, logFixture.allLogs()));
            }
        };
    }
}
