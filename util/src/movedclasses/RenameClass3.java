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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.thoughtworks.go.util.Procedure;

/**
 * @understands handling of multiple dynamically-created readWriteLocks
 */
public class RenameClass3 {
    private Map<String, ReadWriteLock> locks = new HashMap<String, ReadWriteLock>();

    public void renameMethod4(String key) {
        getLock(key).readLock().unlock();
    }

    public void releaseWriteLock(String key) {
        getLock(key).writeLock().unlock();
    }

    private ReadWriteLock getLock(String key) {
        synchronized (key.intern()) {
            if (locks.containsKey(key)) {
                return locks.get(key);
            }
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            locks.put(key, lock);
            return lock;
        }
    }

    public void withReadLock(String mutex, Procedure procedure) {
        getLock(mutex).readLock().lock();
        try {
            procedure.call();
        } finally {
            renameMethod4(mutex);
        }
    }
}
