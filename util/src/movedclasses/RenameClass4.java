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


package movedclasses;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggingEvent;

public class RenameClass4 extends ExtractedSuperClass {

    public static RenameClass4 startListening() {
        return startListening(Level.DEBUG);
    }

    public static RenameClass4 startListening(Level level) {
        RenameClass4 testing = new RenameClass4();
        testing.activateOptions();
        setLevel(level);
        Logger.getRootLogger().addAppender(testing);
        return testing;
    }

    public void stopListening() {
        Logger.getRootLogger().removeAppender(this);
    }

    public static Level getLevel() {
        return Logger.getRootLogger().getLevel();
    }

    public static void enableDebug() {
        setLevel(Level.DEBUG);
    }

    public static void setLevel(Level level) {
        Logger.getRootLogger().setLevel(level);
    }

    private RenameClass4() {
    }

    protected synchronized void append(LoggingEvent event) {
        events.add(event);
        messages.add(event.getRenderedMessage());
    }

    public synchronized boolean contains(Level level, String message) {
        for (LoggingEvent event : events) {
            if (event.getLevel().equals(level) && event.getMessage().toString().contains(message)) {
                return true;
            }
        }
        return false;
    }

    public synchronized String allLogs() {
        StringBuilder builder = new StringBuilder();
        for (LoggingEvent event : events) {
            builder.append(event.getLevel()).append(" - ").append(event.getMessage()).append("\n");
            if (event.getThrowableInformation() != null) {
                for (String s : event.getThrowableStrRep()) {
                    builder.append(s).append("\n");
                }
            }
        }
        return builder.toString();
    }
}
