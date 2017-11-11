/*
 * This is main file which contains information about three sources and when one will 
   run the code you will two options 
   This class has two functions i.e.  1 FOR FILE SEARCH AND 2 FOR EXTENSION SEARCH
   1. Filename search:- which will output 3 text file which contains information about 
      file present by given name in  3 differnt sources.
   2. Extemsion Search :- which will output three text files that will contain all the files
      with that given extension and information about that extension.
    I am using sql lite extension to store data about all extensions.
    I am also using MultiThraeding to get information about filename simultanously bt three differnt sources
    
 */
package filetype;


import javax.swing.JOptionPane;
/**
 *
 * @author Dhwaj Verma
 */
public class Filetype 
{
    //Sources on which file searching will be done
     String source1="";
     String source2="";
     String source3="";
     source1 sc1=null;
     source2 sc2=null;
     source3 sc3=null;
    // Main Function
    public Filetype(String src1,String src2,String src3)
    {
        this.source1=src1;
        this.source2=src2;
        this.source3=src3;
    }
    
    public void fileSearch()
    {
        //if you select option 1 i.e Filename search
        String filename=JOptionPane.showInputDialog("Enter the Filename");// it will show input pane to enter file name
        sc1= new source1(source1, filename);
        sc1.start(); // start() will start the thread1
        sc2=new source2(source2, filename);
        sc2.start(); // start() will start the thread2
        sc3=new source3(source3, filename);
        sc3.start(); // start() will start the thread3
    }
    
    public void extSearch()
    {
           //if You select option 2 i.e. Extension search
           String ext=JOptionPane.showInputDialog("Enter the Extension");// it will show input pane to enter extension name
           extSearch ext1=new extSearch(ext, source1, source2, source3);
    }
}

