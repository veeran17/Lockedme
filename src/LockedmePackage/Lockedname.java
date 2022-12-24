package LockedmePackage;

import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
import java.io.IOException;

public class Lockedname {
	 static String DIRECTORY;
	    File folder_name;

	    public Lockedname() {
	        DIRECTORY = System.getProperty("user.dir");
	        folder_name = new File(DIRECTORY+"/files");
	        if (!folder_name.exists())
	            folder_name.mkdirs();
	    }

	    private static final String WELCOME_PROMPT =
	            "\n Phase1 Project : Lockedname\n"+
	                    "\n Developer : Veeran Kota\n";

	    private static final String MAIN_MENU_PROMPT =
	            "\nMAIN MENU - Choose the Following below : \n"+
	                    "1. List files in directory\n"+
	                    "2. Add, Delete or Search\n"+
	                    "3. Exit Program";

	    private static final String SECONDARY_MENU_PROMPT =
	            "   \nSelect any of the following: \n"+
	                    "   1. Add a file\n"+
	                    "   2. Delete a file\n"+
	                    "   3. Search a file\n"+
	                    "   4. GoBack";

	    void showPrimaryMenu() {
	        System.out.println(MAIN_MENU_PROMPT);
	        try{
	            Scanner scanner = new Scanner(System.in);
	            int option = scanner.nextInt();
	            switch (option){
	                case 1 : {
	                    showFiles();
	                    showPrimaryMenu();
	                }
	                case 2 : {
	                    showSecondaryMenu();
	                }
	                case 3 : {
	                    System.out.println("Thanks");
	                    System.exit(0);
	                }
	                default: showPrimaryMenu();
	            }
	        }
	        catch (Exception e){
	            System.out.println("Please enter 1, 2 or 3");
	            showPrimaryMenu();
	        }
	    }

	    void showSecondaryMenu() {
	        System.out.println(SECONDARY_MENU_PROMPT);
	        try{
	            Scanner scanner = new Scanner(System.in);
	            int option = scanner.nextInt();

	            switch (option){
	                case 1 : {
	                    System.out.print("Adding a file...Please Enter a File Name : ");
	                    String filename = scanner.next().trim().toLowerCase();
	                    addFile(filename);
	                    break;
	                }
	                case 2 : {
	                    System.out.print("Deleting a file...Please Enter a File Name : ");
	                    String filename = scanner.next().trim();
	                    deleteFile(filename);
	                    break;
	                }
	                case 3 : {
	                    System.out.print("Searching a file...Please Enter a File Name : ");
	                    String filename = scanner.next().trim();
	                    searchFile(filename);
	                    break;
	                }
	                case 4 : {
	                    System.out.println("Going Back to MAIN menu");
	                    showPrimaryMenu();
	                    break;
	                }
	                default : System.out.println("Please enter 1, 2, 3 or 4");
	            }
	            showSecondaryMenu();
	        }
	        catch (Exception e){
	            System.out.println("Please enter 1, 2, 3 or 4");
	            showSecondaryMenu();
	        }
	    }
	    
	    void showFiles() {
	        if (folder_name.list().length==0)
	            System.out.println("The folder is empty");
	        else {
	            String[] list = folder_name.list();
	            System.out.println("The files in present are :");
	            Arrays.sort(list);
	            for (String str:list) {
	                System.out.println(str);
	            }
	        }
	    }
	    
	    void searchFile(String filename) {
	        String[] list = folder_name.list();
	        for (String file: list) {
	            if (filename.equals(file)) {
	                System.out.println("FOUND : File " + filename + " exists ");
	                return;
	            }
	        }
	        System.out.println("File NOT found (FNF)");
	    }

	    void addFile(String filename) throws IOException {
	        File filepath = new File(folder_name +"/"+filename);
	        String[] list = folder_name.list();
	        for (String file: list) {
	            if (filename.equalsIgnoreCase(file)) {
	                System.out.println("File " + filename + " already exists ");
	                return;
	            }
	        }
	        filepath.createNewFile();
	        System.out.println("File "+filename+" added ");
	    }
	    
	    void deleteFile(String filename) {
	        File filepath = new File(folder_name +"/"+filename);
	        String[] list = folder_name.list();
	        for (String file: list) {
	            if (filename.equals(file) && filepath.delete()) {
	                System.out.println("File " + filename + " deleted ");
	                return;
	            }
	        }
	        System.out.println("Delete Operation failed. FILE NOT FOUND");
	    }



	    public static void main(String[] args) {
	        System.out.println(WELCOME_PROMPT);
	        Lockedname menu = new Lockedname();
	        menu.showPrimaryMenu();
	    }
}
