/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaconsoleapp1;
import java.util.*; 
  // Import the File class
import java.io.IOException;// Import this class to handle errors
import java.io.FileReader;
import java.io.BufferedReader;
/**
 *
 * @author nly
 */
public class Main {
    public static void main(String[] args) throws IOException {
        
        Scanner keyboard  = new Scanner(System.in);
        
        System.out.println("Seçenekler:\n 1)Dosyadan öğrenci bilgileri oku.\n" +
"2) Klavyeden öğrenci bilgileri ekle.\n" +
"3) Klavyeden adı ve soyadı girilen bir öğrencinin (varsa aynı ad soyada sahip tüm öğrencilerin)" +
"bilgileri ekrana yazdır.\n" +
"4) Öğrenci numarası girilen öğrenciyi listeden sil.\n" +
"5) Listenin içindeki tüm kayıtları artan sırada (küçük öğrenci numarasından büyük öğrenci" +
"numarasına) ekrana yazdır.\n" +
"6) Listenin içindeki tüm kayıtları azalan sırada (büyük öğrenci numarasından küçük öğrenci" +
"numarasına) ekrana yazdır.\n" +
"7) ÇIKIŞ: Programdan çık.");
        
        boolean devam = true;
        
        //create a DoublyLinkedList object
        MyLinkedList dl_List = new MyLinkedList();  
        
        
        while(devam == true) {  
            
            System.out.println("Yapmak istediğiniz işlemin sayısını giriniz:");          
            int sayi = keyboard.nextInt();
            

            
            switch (sayi) {
            case 1:  //dosya okuma
                    //ınt oku, string oku string oku,(string oku), telefon oku,(tel oku)
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader("ogrenciler.txt"))) {

			String line = null;

			String specialCharacter = ",";

			ArrayList<String> telNolari = new ArrayList<>();

			while ((line = bufferedReader.readLine()) != null) {

				System.out.println(line);

				String[] values = line.split(specialCharacter);
                                int i;
                                Student ogrenci = new Student();
                                int okulNo = Integer.parseInt(values[0]);
                                ogrenci.setOgrenciNo(okulNo);
                                ogrenci.setAdSoyad(values[1]);                           
                                
                                //Add nodes to the list  

                                dl_List.InsertNode(ogrenci);  
                        }
                        
                }
                        
                    break;
            case 2:  //öğrenci veri alma
                    System.out.println("Öğrencinin okul numarasını giriniz:");
                    int ogrenciNo = keyboard.nextInt();
                    keyboard.nextLine();
                    System.out.println("Öğrencinin adını soyadını giriniz:");
                    String adSoyad = keyboard.nextLine();
                    ArrayList<String> telefonlar = new ArrayList<>();
                    String baskaNum = "Y";
                    do{
                        System.out.println("Öğrencinin telefon numarasını giriniz:");
                        String telefon = keyboard.nextLine();
                        
                        telefonlar.add(telefon);
                        System.out.println("Öğrencinin başka telefon numarası var mı?(Y/N)");
                        baskaNum = keyboard.nextLine();

                    }while(baskaNum.equals("Y")||baskaNum.equals("y"));
                    
                    
                    Student nextstudent = new Student(ogrenciNo, adSoyad, telefonlar);
                    
                    //Add nodes to the list  

                    dl_List.InsertNode(nextstudent);  
                    
                     break;
                     
            case 3:  //adı yazılan öğrenciyi bulma
                    System.out.println("Bilgilerini öğrenmek istediğiniz"
                            + " öğrencinin adını soyadını giriniz:");
                    keyboard.nextLine();
                    String bulunacakOgrenci = keyboard.nextLine();                    
                    dl_List.FindStudent(bulunacakOgrenci);
                    
                     break;
            case 4:  //numarası verilen öğrenciyi silme
                    System.out.println("Silmek istediğiniz öğrencinin "
                            + "okul numarasını giriniz:");
                    keyboard.nextLine();
                    int silinecekOgrenci = keyboard.nextInt();
                    //keyboard.nextLine();
                    dl_List.deleteNode(silinecekOgrenci);
                    
                     break;
            case 5:  //artan sıralı yazdır                    
                    dl_List.printHeadToTailNodes();                
                     break;
            case 6:  //azalan sıralı yazdır
                    dl_List.printTailToHeadNodes();
                     break;
            case 7:  //çıkış
                    devam = false ;
                     break;
            }
            
            
            
            


            //print the nodes of DoublyLinkedList  
            //dl_List.printNodes();  
            
            //telefonArraylist.add
            
            //case 7 devam = false çıkış yapılır.
            
        } 
    }
}
