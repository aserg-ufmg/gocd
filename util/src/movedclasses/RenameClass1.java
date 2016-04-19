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

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.thoughtworks.go.util.ListUtil.join;

public class RenameClass1 {
    private final List<RenameClass2> data = new ArrayList<RenameClass2>();
	private static String[] field;

    public static RenameClass1 fromString(String csvContent) {
        RenameClass1 csv = new RenameClass1();
        field = csvContent.split("\n");
        if (field.length > 1) {
            String header = field[0];
            String[] columns = header.split(",");

            for (int i = 1; i < field.length; i++) {
                String line = field[i];
                RenameClass2 row = csv.renameMethod1();
                String[] strings = line.split(",");
                for (int j = 0; j < strings.length; j++) {
                    String string = strings[j];
                    row.renameMethod2(columns[j], string);
                }
            }
        }
        return csv;
    }

    public RenameClass2 renameMethod1() {
        RenameClass2 newRow = new RenameClass2();
        data.add(newRow);
        return newRow;
    }

    public String toString() {
        Set<String> allFields = fields();
        StringBuffer sb = new StringBuffer();
        sb.append(join(allFields, ",")).append("\n");
        for (RenameClass2 row : data) {
            sb.append(row.toString(allFields)).append("\n");
        }
        return sb.toString();
    }

    private Set<String> fields() {
        Set<String> fields = new LinkedHashSet<String>();
        for (RenameClass2 row : data) {
            fields.addAll(row.fields());
        }
        return fields;
    }

    public int rowCount() {
        return data.size();
    }

    /**
     * Test if this Csv contains specified row.
     * Note: Provided row may only contain part of the columns.
     * @param row Each row is represented as a map, with column name as key, and column value as value
     * @return
     */
    public boolean containsRow(Map<String, String> row) {
        for (RenameClass2 csvRow : data) {
            if (csvRow.contains(row)) {
                return true;
            }
        }
        return false;
    }
}
