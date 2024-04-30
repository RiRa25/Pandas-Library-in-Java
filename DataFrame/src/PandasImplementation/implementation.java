package PandasImplementation;

import java.io.IOException;

import Pandas.DataFrame;
import Pandas.DataFrame.*;
import java.util.*;
public class implementation {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		
		DataFrame df = new DataFrame();
		//df.read_csv("C:\\Users\\richa\\Downloads\\outputfinal.csv");
		
		System.out.println("Enter the path of the csv file : ");
		String path = sc.next();
		df.read_csv(path);
		
		int a = 1;
		
		while(a==1)
		{
			
			System.out.println();
			System.out.println("---------- MENU ----------");
			System.out.println("1. Sort by Column values");
			System.out.println("2. Delete Column");
			System.out.println("3. Delete Row");
			System.out.println("4. Delete Rows in range");
			System.out.println("5. Get Column");
			System.out.println("6. Get Row");
			System.out.println("7. Rename Column");
			System.out.println("8. Get Shape");
			System.out.println("9. Get Info");
			System.out.println("10. Replace Columns value");
			System.out.println("11. Get all Column Names");
			System.out.println("12. Get Default Head");
			System.out.println("13. Get Custom Head");
			System.out.println("14. Get Default Tail");
			System.out.println("15. Get Custom Tail");
			System.out.println("16. Generate CSV");
			System.out.println("17. Exit");
			System.out.println();
			
			System.out.println("Enter your choice : ");
			int choice = sc.nextInt();
			
			switch(choice)
			{
				
				case 1:
					System.out.println();
					System.out.println("----- Sort by Column values -----");
					System.out.println("Enter the column name you want to sort : ");
					df.sort_values(sc.next(), false);
					break;
					
				case 2:
					System.out.println();
					System.out.println("----- Delete Column -----");
					System.out.println("Enter the column name you want to delete : ");
					df.deleteColumn(sc.next());
					break;
					
				case 3:
					System.out.println();
					System.out.println("----- Delete Row -----");
					System.out.println("Enter the sample index you want to delete : ");
					df.deleteRow(sc.nextInt());
					break;
					
				case 4:
					System.out.println();
					System.out.println("----- Delete Rows in Range -----");
					System.out.println("Enter start index : ");
					int start = sc.nextInt();
					System.out.println("Enter end index : ");
					int end = sc.nextInt();
					df.deleteRows(start, end);
					break;
					
				case 5:
					System.out.println();
					System.out.println("----- Get Column -----");
					System.out.println("Enter the Column name you want to view:");
					List<Object> column = df.getColumn(sc.next());
					Iterator it = column.iterator();
					 
			        while (it.hasNext())
			        {
			            System.out.println(it.next());
			        }
					break;
					
				case 6:
					System.out.println();
					System.out.println("----- Get Row -----");
					System.out.println("Enter the row index you want to view:");
					Map<String, Object> row = df.getRow(sc.nextInt());
					for (Map.Entry<String, Object> set : row.entrySet()) {
			 
			            System.out.println(set.getKey() + " : "+ set.getValue());
			        }
					break;
					
				case 7:
					System.out.println();
					System.out.println("----- Rename Column -----");
					System.out.println("Enter the initial column name : ");
					String ini = sc.next();
					String commit = sc.next();
					df.renameColumn(ini, commit);
					break;
					
				case 8:
					System.out.println();
					System.out.println("----- Get Shape -----");
					int arr[] = df.getShape();
					System.out.println("Number of Rows:" + arr[0]);
					System.out.println("Number of Columns:" + arr[1]);
					break;
					
				case 9:
					System.out.println();
					System.out.println("----- Get Info -----");
					df.info();
					break;
					
				case 10:
					System.out.println();
					System.out.println("----- Replace Columns value -----");
					System.out.println("Enter Column Name : ");
					String colname = sc.next();
					System.out.println("Enter the String you want to replace : ");
					Object replace = sc.next();
					System.out.println("Enter the value you want to replace with");
					Object with = sc.next();
					
					df.replace(colname, replace, with);
					break;
					
				case 11:
					System.out.println();
					System.out.println("----- Get all Column Names -----");
					System.out.println("Get Column Names");
					Set<String> colname1 = df.getColumns();
					Iterator<String> namesIterator = colname1.iterator();
					while(namesIterator.hasNext()) {
						System.out.println(namesIterator.next());
					}
					break;
					
				case 12:
					System.out.println();
					System.out.println("----- Get Default Head -----");
					df.displayDataTabular(df.head());
					System.out.println();
					break;
					
				case 13:
					System.out.println();
					System.out.println("----- Get Custom Head -----");
					System.out.println("Enter Number of Samples: ");
					df.displayDataTabular(df.head(sc.nextInt()));
					System.out.println();
					break;
					
				case 14:
					System.out.println();
					System.out.println("----- Get Default Tail -----");
					df.displayDataTabular(df.tail());
					System.out.println();
					break;
					
				case 15:
					System.out.println();
					System.out.println("----- Get Custom Head -----");
					System.out.println("Enter Number of Samples: ");
					df.displayDataTabular(df.tail(sc.nextInt()));
					System.out.println();
					break;
					
				case 16:
					System.out.println();
					System.out.println("----- Generate CSV -----");
					System.out.println("Enter the file name: ");
					df.toCSV(sc.next());
					break;
					
				case 17:
					System.out.println();
					System.out.println("----- Exit -----");
					System.out.println("Thank You");
					a = 0;
					break;
				
			}
		}
		
		
	}

}
