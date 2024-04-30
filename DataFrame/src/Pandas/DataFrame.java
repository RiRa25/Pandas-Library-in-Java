package Pandas;
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;

public class DataFrame {
	
	public List<Map<String, Object>> data;
    public Set<String> columns;

    public DataFrame() {
        this.data = new ArrayList<>();
        this.columns = new HashSet<>();
    }
    
    public void addColumn(String columnName, Object defaultValue) {
        columns.add(columnName);
        for (Map<String, Object> row : data) {
            row.put(columnName, defaultValue);
        }
    }
    
    public void replace(String columnName, Object oldValue, Object newValue) {
        for (Map<String, Object> row : data) {
            if (row.containsKey(columnName) && Objects.equals(row.get(columnName), oldValue)) {
                row.put(columnName, newValue); 
            }
        }
    }
    
    public List<String> columns() {
        List<String> columnList = new ArrayList<>(columns);
        Collections.sort(columnList); 
        return columnList;
    }


    public void addRow(Map<String, Object> row) {
        for (String column : columns) {
            row.putIfAbsent(column, null); 
        }
        data.add(row);
    }
    
    public int[] getShape() {
        int numRows = data.size(); 
        int numColumns = columns.size(); 
        return new int[]{numRows, numColumns}; 
    }
    
    public void deleteColumn(String colName) {
        if (columns.contains(colName)) { 
            columns.remove(colName);
            for (Map<String, Object> entity : data) {
                entity.remove(colName); 
            }
        } else {
            System.out.println("Column " + colName + " does not exist.");
        }
    }

    public void deleteRow(int index) {
        if (index >= 0 && index < data.size()) {
            data.remove(index); 
        } else {
            throw new IndexOutOfBoundsException("Index " + index + " is out of range.");
        }
    }
 // Retrieve a specific column
    public List<Object> getColumn(String columnName) {
        if (!columns.contains(columnName)) {
            throw new IllegalArgumentException("Column does not exist");
        }

        List<Object> columnData = new ArrayList<>();
        for (Map<String, Object> row : data) {
            columnData.add(row.get(columnName));
        }
        return columnData;
    }
    
    public void renameColumn(String oldName, String newName) {
        if (!columns.contains(oldName)) {
            throw new IllegalArgumentException("Column '" + oldName + "' does not exist.");
        }
        if (columns.contains(newName)) {
            throw new IllegalArgumentException("Column '" + newName + "' already exists.");
        }

        
        columns.remove(oldName);
        columns.add(newName);

        
        for (Map<String, Object> row : data) {
            if (row.containsKey(oldName)) {
                row.put(newName, row.remove(oldName)); 
            }
        }
    }
    
    public Map<String, Object> getRow(int index) {
        if (index < 0 || index >= data.size()) {
            throw new IndexOutOfBoundsException("Row index out of bounds");
        }

        return data.get(index);
    }

    
    public DataFrame filter(String columnName, Object value) {
        DataFrame filtered = new DataFrame();
        filtered.addColumn(columnName, null);

        for (Map<String, Object> row : data) {
            if (Objects.equals(row.get(columnName), value)) {
                filtered.addRow(new HashMap<>(row));
            }
        }

        return filtered;
    }
    
    public void read_csv(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IllegalStateException("CSV file is empty");
            }

            
            String[] headers = headerLine.split(",");
            for (String columnName : headers) {
                addColumn(columnName.trim(), null); 
            }

            
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] rowValues = line.split(",");

                if (rowValues.length != headers.length) {
                    throw new IllegalArgumentException("Row length does not match header length");
                }

                
                Map<String, Object> row = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    row.put(headers[i].trim(), rowValues[i].trim());
                }

                addRow(row); 
            }
        }
    }
    
    public List<Map<String, Object>> head(int num) {
    	if(num > 0)
    	{
    		int numRows = Math.min(data.size(), num); 
    		return data.subList(0, numRows); 
    	}
    	else
    	{
    		return tail((-1)*num);
    	}
    }
    
    public List<Map<String, Object>> head() {
    	
    	int numRows = Math.min(data.size(), 5); 
   		return data.subList(0, numRows); 
    
    }
    
    public List<Map<String, Object>> tail(int num) {
        int totalRows = data.size();
        int start = Math.max(0, totalRows - num); 
        return data.subList(start, totalRows);
    }
    
    public List<Map<String, Object>> tail(){
        int totalRows = data.size();
        int start = Math.max(0, totalRows - 5); 
        return data.subList(start, totalRows);
    }

    
    public void displayDataTabular(List<Map<String, Object>> rows) {
        if (rows.isEmpty()) {
            System.out.println("No data available");
            return;
        }

        
        List<String> columnList = new ArrayList<>(columns);

        
        Map<String, Integer> columnWidths = new HashMap<>();
        for (String column : columnList) {
            int maxLength = column.length();
            for (Map<String, Object> row : rows) {
                Object value = row.get(column);
                int valueLength = (value == null) ? 4 : value.toString().length(); 
                if (valueLength > maxLength) {
                    maxLength = valueLength;
                }
            }
            columnWidths.put(column, maxLength);
        }

        
        StringBuilder formatBuilder = new StringBuilder();
        for (String column : columnList) {
            formatBuilder.append("%-").append(columnWidths.get(column)).append("s | ");
        }
        String formatString = formatBuilder.toString();

        
        System.out.println();
        System.out.format(formatString, columnList.toArray());

        
        StringBuilder separator = new StringBuilder();
        for (String column : columnList) {
            int width = columnWidths.get(column) + 3; 
            separator.append("-".repeat(width));
        }
        System.out.println();
        System.out.println(separator);

        
        for (Map<String, Object> row : rows) {
            List<Object> rowData = new ArrayList<>();
            for (String column : columnList) {
                Object value = row.get(column);
                rowData.add(value == null ? "null" : value.toString());
            }
            System.out.format(formatString, rowData.toArray());
        }
        System.out.println();
    }
    
    public void info() {
        if (data == null || data.isEmpty()) {
            System.out.println("No data available");
            return;
        }

        int totalRows = data.size();
        System.out.println("Total rows: " + totalRows);

        
        Set<String> uniqueColumns = new HashSet<>();
        for (Map<String, Object> row : data) {
            uniqueColumns.addAll(row.keySet());
        }
        List<String> columnNames = new ArrayList<>(uniqueColumns);

        System.out.println("Column Information:");
        Map<String, Integer> nonNullCounts = new HashMap<>();
        Map<String, Class<?>> columnTypes = new HashMap<>();

        
        for (String column : columnNames) {
            int nonNullCount = 0;
            Class<?> columnType = null;

            for (Map<String, Object> row : data) {
                Object value = row.get(column);
                if (value != null) {
                    nonNullCount++;
                    if (columnType == null) {
                        columnType = value.getClass();
                    } else if (!columnType.equals(value.getClass())) {
                        columnType = Object.class; 
                    }
                }
            }

            nonNullCounts.put(column, nonNullCount);
            columnTypes.put(column, columnType);
        }

        for (String column : columnNames) {
            int nonNullCount = nonNullCounts.get(column);
            Class<?> columnType = columnTypes.get(column);
            System.out.printf("  - %s: %d non-null, type: %s%n", column, nonNullCount, 
                               (columnType == null) ? "Unknown" : columnType.getSimpleName());
        }

        
        long estimatedMemoryUsage = totalRows * columnNames.size() * 8; 
        System.out.println("Estimated memory usage: " + estimatedMemoryUsage + " bytes");
    }

    
    public void toCSV(String fileName) throws IOException {
    	String str = fileName + ".csv";
    	File myFile = new File(str);
		try {
			myFile.createNewFile();
			System.out.println("File created at: " + myFile.getAbsolutePath());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
        try (FileWriter writer = new FileWriter(str)) {
            
            List<String> columnNames = new ArrayList<>(columns);
            Collections.sort(columnNames); 
            writer.write(String.join(",", columnNames) + "\n"); 

            
            for (Map<String, Object> row : data) {
                List<String> values = new ArrayList<>();
                for (String column : columnNames) {
                    Object value = row.get(column);
                    if (value == null) {
                        values.add(""); 
                    } else {
                        String strValue = value.toString();
                        
                        if (strValue.contains(",") || strValue.contains("\"")) {
                            strValue = "\"" + strValue.replace("\"", "\"\"") + "\""; 
                        }
                        values.add(strValue);
                    }
                }
                writer.write(String.join(",", values) + "\n"); 
            }
        }
    }
    
    public void sort_values(String columnName, boolean ascending) {
        if (!columns.contains(columnName)) {
            throw new IllegalArgumentException("Column " + columnName + " not found");
        }

        Comparator<Map<String, Object>> comparator = (row1, row2) -> {
            Object value1 = row1.get(columnName);
            Object value2 = row2.get(columnName);

            
            if (value1 == null && value2 == null) {
                return 0;
            }
            if (value1 == null) {
                return ascending ? -1 : 1; 
            }
            if (value2 == null) {
                return ascending ? 1 : -1; 
            }

            if (value1 instanceof Comparable && value2 instanceof Comparable) {
                return ((Comparable<Object>) value1).compareTo(value2) * (ascending ? 1 : -1);
            }

            throw new IllegalArgumentException("Values in column " + columnName + " are not comparable");
        };

        data.sort(comparator); 
    }
    
    public void deleteRows(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex >= data.size() || startIndex > endIndex) {
            throw new IllegalArgumentException("Invalid index range for deletion");
        }

        
        data.subList(startIndex, endIndex + 1).clear(); 
    }


    public List<Map<String, Object>> getData() {
        return data;
    }

    public Set<String> getColumns() {
        return columns;
    }
    
    
}
