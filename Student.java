/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaconsoleapp1;
import java.util.*; 
/**
 *
 * @author nly
 */
public class Student {
    private int ogrenciNo;
    private String adSoyad;
    private ArrayList<String> telefonArraylist = new ArrayList<>();
    
    //parametreli constructor
    public Student(int StudentNo, String Name,ArrayList<String> telefonlar)
    {
	this.ogrenciNo = StudentNo;
   	this.adSoyad = Name;
        this.telefonArraylist = telefonlar; 
    }
    
    //parametresiz constructor
    public Student() {
        ogrenciNo = 0;
        adSoyad = "" ;
        telefonArraylist = null;        
    }
    
       
    //copy construstor
    
    public Student(Student ogrenci)
    { 
        this.ogrenciNo = ogrenci.getOgrenciNo();
   	this.adSoyad = ogrenci.getAdSoyad();
        this.telefonArraylist = ogrenci.getTelNo(); 
    }
    
    
 
	//get methodu
    public String getAdSoyad() { return adSoyad; }
    
    public int getOgrenciNo() { return ogrenciNo; }

    public ArrayList<String> getTelNo() { return telefonArraylist; }
	
	//set methodu
    public void setAdSoyad(String N)
    {
        this.adSoyad = N;
    }
    
    public void setOgrenciNo(int N)
    {
        this.ogrenciNo = N;
    }
        
    public void setTelNo(ArrayList<String> N)
    {
        this.telefonArraylist = N;
    }
    
    
	
	//toString methodu(Student bilgilerini yazdırır)
    public void ToString()
    {
        System.out.println( "Öğrenci Numarası= " + ogrenciNo + "\nAdı Soyadı ="
                + adSoyad + "\nTelefon Numarası= " + telefonArraylist);
    }
    
}
