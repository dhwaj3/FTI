/*
 * This File is Thread 3 which will search File by given file name in Source 1 and output file with log source3.txt
 */
package filetype;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dhwajverma
 */
public class source3 extends Thread
{
    String source= "";
    String filename="";
    Connection c=null;
    File fi=new File("logSource3.txt");
    // Parametrized Constructor
    public source3(String source,String filename) 
    {
        this.source=source;
        this.filename=filename;
        if(fi.exists())
            fi.delete();
    }
    // Thread Class run function  which is overriiden
    @Override
    public void run()
    {
        try {
            File folder = new File(source);
            File[] listOfFiles = folder.listFiles();
            List<String> fileList=new ArrayList<String>();
            System.out.println("length 3== "+listOfFiles.length);
            
        
           {
            for(int i=0;i<listOfFiles.length;i++)
            {
                //Making the list of Files in Source 3
                File f=listOfFiles[i];
                if(f.isFile())
                {
                    String s=f.getName();
                    //Splitting the String on dot(.) i.e first part is file name and second part is file extension
                    String arr[]=s.split("\\.");
                    if(arr[0].equalsIgnoreCase(filename))
                    {
                        fileList.add(f.getName());
                        System.out.println("File found");
                        System.out.println("file name=="+f.getName());
                        System.out.println("File extension== "+arr[1]);
                        System.out.println("file parent=="+f.getParent());
                        try {
                            // Declaring name of database i.e. SQl lite
                            Class.forName("org.sqlite.JDBC");
                            File temp = new File("fileinfo.db");
                            String conn = "jdbc:sqlite:"+ temp.getAbsolutePath().replace("\\","\\\\");
                            //Making connetion with database whose name is fileinfo.db 
                            c = DriverManager.getConnection(""+conn);
                            PreparedStatement stmt=c.prepareStatement("select * from fileinfo where ext = ?");
                            stmt.setString(1,arr[1]);
                            ResultSet rs = stmt.executeQuery();
                            File fi2=new File("logSource3.txt");
                            // SQL querry to retrieve  extension information from database
                            FileWriter writer = new FileWriter(fi2, true);
                            int x=1;
                            while (rs.next())
                            {
                                String  ext = rs.getString(2);
                                String dec  = rs.getString(3);
                                String  category = rs.getString(4);
                                String lang = rs.getString(5);
                                String paradigm=rs.getString(6);
                                String application=rs.getString(7);
                                //Write all Information on text file logsource1.txt
                                System.out.println("********* "+"%%%"+" **********");
                                writer.write("********* "+"###"+" **********");
                                writer.write("\r\n");
                                writer.write("File name =="+f.getName());
                                writer.write("\r\n");
                                writer.write("File Path =="+f.getAbsolutePath());
                                writer.write("\r\n");
                                System.out.println( "Extension = " + ext );
                                writer.write("Extension = " + ext);
                                writer.write("\r\n");
                                System.out.println( "Description = " + dec );
                                writer.write("Description = " + dec);
                                writer.write("\r\n");
                                System.out.println( "Category = " + category );
                                writer.write("Category = " + category);
                                writer.write("\r\n");
                                System.out.println( "Language = " + lang );
                                writer.write("Language = " + lang);
                                writer.write("\r\n");
                                System.out.println( "Paradigm = " + paradigm );
                                writer.write("Paradigm = " + paradigm);
                                writer.write("\r\n");
                                System.out.println("Application Associated = "+application);
                                writer.write("Application Associated = "+application);
                                writer.write("\r\n");
                                writer.close();
                            }
                        } catch (ClassNotFoundException ex) {
                           ex.printStackTrace();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        } catch (IOException ex) {
                           ex.printStackTrace();
                        }
                    }
                }
            }
            if(fileList.isEmpty())
            {
                //if Fle is empty then it will write No such File existed
                File f2=new File("logSource3.txt");
                FileWriter writer = new FileWriter(f2, true);
                writer.write("No file exited with such name in Source 3");
                writer.close();
                Desktop.getDesktop().open(f2);
            }
            else
                 //This will open txt file 
               Desktop.getDesktop().open(fi);
        }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        
        
    }
}
