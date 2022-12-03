/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaconsoleapp1;

/**
 *
 * @author nly
 */
public class MyLinkedList {
    //A node class for doubly linked list
    private class Node{  
        private Student data;  
        private Node onceki;  
        private Node sonraki;  
   
        public Node(Student ogrenci) {  
            this.data = ogrenci;  
        }
                
        public Student getStudent() { return data; }
        
        
    }  
    //Initially, heade and tail is set to null
    Node head, tail = null;  
   

    public void InsertNode(Student ogrenci) { 
        

        //Create a new node  
        Node newNode = new Node(ogrenci);  
   
        Node current;
        
        
        //if list is empty, head and tail points to newNode  
        if(head == null) {  
            head = tail = newNode;  
 
        }  
        
        
        else if (head.getStudent().getOgrenciNo() >= newNode.getStudent().getOgrenciNo())
            {
                newNode.sonraki = head;
                newNode.sonraki.onceki = newNode;
                head = newNode;
                tail = newNode.sonraki;
                
            }

        else
            {
                current = head;
 
                // locate the node after which the new node
                // is to be inserted
                while (current.sonraki != null &&
                        current.sonraki.getStudent().getOgrenciNo() < newNode.getStudent().getOgrenciNo())
                    current = current.sonraki;
 
                /* Make the appropriate links */
                newNode.sonraki = current.sonraki;
 
                // if the new node is not inserted
                // at the end of the list
                if (current.sonraki != null)
                    newNode.sonraki.onceki = newNode;
 
                current.sonraki = newNode;
                newNode.onceki = current;
                
                if(newNode.sonraki == null){
                    tail = newNode;
                        
                }
             
            }    
        
        //head's previous will be null  
        //head.onceki = null;  
        //tail's next will be null  
        //tail.sonraki = null; 

    }  
    //////////////////////////
    //
    //* Adı ve soyadı verilen bir öğrenciyi listede arayarak 
    //eğer varsa bu öğrencinin (varsa aynı ad ve soyada sahip diğer 
    //öğrencilerin de) bilgilerini yazdıran metot
    //
    //
    ///////////////////
    
    public void FindStudent(String ogrenciAdSoyad) {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");             
        }  
        System.out.println( ogrenciAdSoyad +"adındaki öğrenciler:\n ");  
        while(current != null) {
            
            if (current.getStudent().getAdSoyad().equals(ogrenciAdSoyad)){
                
                current.getStudent().ToString();            
            }
            //Print each node and then go to next.  

            current = current.sonraki;  
        }  
    } 
    

    //istenen nodun silinmesini sağlayan method
    private void deleteNode(Node del)
    {
        // base case
        if (head == null || del == null)
            return;
 
        // If node to be deleted is head node
        if (head == del)
            head = del.sonraki;
 
        // Change next only if node to be
        // deleted is NOT the last node
        if (del.sonraki != null)
            del.sonraki.onceki = del.onceki;
 
        // Change prev only if node to be
        // deleted is NOT the first node
        if (del.onceki != null)
            del.onceki.sonraki = del.sonraki;
 
        return;
    }
 
    //* Öğrenci numarası verilen öğrencinin listeden 
    //silinmesini sağlayan silme metodu    
    // Function to delete the node at the
    // given position in the doubly linked list
    public void deleteNode(int ogrenciNo)
    {
        /* if list in NULL or
          invalid position is given */
        if (head == null || ogrenciNo <= 0)
            return;
 
        //currentı head olarak ayarlarız
        Node current = head;
 
        /*
        * traverse up to the node at
          position 'n' from the beginning
        */
        while ( current != null )
        {
            if(current.getStudent().getOgrenciNo() == ogrenciNo){
                // delete the node pointed to by 'current'
                deleteNode(current);
                current = current.sonraki;
            }
            
            else{
              //sonraki noda geç
                current = current.sonraki;
            }

        }
         
 

    }
    
    
    
    
//Studentın ogrenci no küçükten büyüğe yazdır methodunu listteki her student için çalıştıran method
    public void printHeadToTailNodes() {  
        //Node current will point to head  
        Node current = head;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");             
        }  
        System.out.println("Nodes of doubly linked list from head to tail: ");  
        while(current != null) {  
            //Print each node and then go to next. 
            
            current.getStudent().ToString();  
            current = current.sonraki; 
            
        }  
    } 
    //Studentın ogrenci no büyükten küçüğe yazdır methodunu listteki her student için çalıştıran method
        public void printTailToHeadNodes() {  
        //Node current will point to head  
        Node current = tail;  
        if(head == null) {  
            System.out.println("Doubly linked list is empty");             
        }  
        System.out.println("Nodes of doubly linked list from tail to head: ");  
        while(current != null) {  
            //Print each node and then go to next.  
            current.getStudent().ToString();  
            current = current.onceki;  
        }  
    } 
}
