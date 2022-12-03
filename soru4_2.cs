using System;

namespace DS_proje4_2
{
    public class Node  // Düğüm sınıfı. Kendi içinde alt düğümleri de (left child, right child) tutmaktadır.
    {
        public int data; // Düğümün değeri
        public Node left;
        public Node right;
        public Node(int data) // Düğüm sınıfı yapılandırıcısı. Düğüm nesnesi, taşıyacağı değer girilerek oluşturulur.
        {
            this.data = data;
        }
    }

    public class AVL
    {
        Node root; // AVL sınıfının kök düğümü

        public AVL() { }// AVL sınıfı yapılandırıcısı. AVL nesnesi herhangi bir argüman almadan oluşturulur.

        public void Add(int data)
        {
            Node newItem = new Node(data);
            if (root == null)
            {
                root = newItem;
            }
            else
            {
                root = RecursiveInsert(root, newItem);
            }
        }

        private Node RecursiveInsert(Node current, Node n)
        {
            if (current == null)
            {
                current = n;
                return current;
            }
            else if (n.data < current.data)
            {
                current.left = RecursiveInsert(current.left, n);
                current = balance_tree(current);
            }
            else if (n.data > current.data)
            {
                current.right = RecursiveInsert(current.right, n);
                current = balance_tree(current);
            }
            return current;
        }

        private Node balance_tree(Node current)
        {
            int b_factor = balance_factor(current);
            if (b_factor > 1)
            {
                if (balance_factor(current.left) > 0)
                {
                    current = RotateLL(current);
                }
                else
                {
                    current = RotateLR(current);
                }
            }
            else if (b_factor < -1)
            {
                if (balance_factor(current.right) > 0)
                {
                    current = RotateRL(current);
                }
                else
                {
                    current = RotateRR(current);
                }
            }
            return current;
        }

        private void InOrder(Node localRoot)
        {
            if (localRoot != null)
            {
                InOrder(localRoot.left);
                Console.Write("({0}) ", localRoot.data);
                InOrder(localRoot.right);
            }
        }

        public void DisplayAVL()
        {
            if (root == null)
            {
                Console.WriteLine("Tree is empty");
                return;
            }
            Console.WriteLine("AVL (Dengeli İkili Arama Ağacı) InOrder Düzende Yazdırılması : \n");
            InOrder(root);
            Console.WriteLine("\n\n\n");
        }

        private int max(int l, int r)
        {
            return l > r ? l : r;
        }

        private int getHeight(Node current)
        {
            int height = 0;
            if (current != null)
            {
                int l = getHeight(current.left);
                int r = getHeight(current.right);
                int m = max(l, r);
                height = m + 1;
            }
            return height;
        }

        private int balance_factor(Node current)
        {
            int l = getHeight(current.left);
            int r = getHeight(current.right);
            int b_factor = l - r;
            return b_factor;
        }

        private Node RotateRR(Node parent)//Right Rotation
        {
            Node pivot = parent.right;// pivot =y 
            parent.right = pivot.left; // y = T2
            pivot.left = parent;// T2 = z
            return pivot;
        }

        private Node RotateLL(Node parent)//Left Rotation
        {
            Node pivot = parent.left; // pivot = y
            parent.left = pivot.right;// y = 
            pivot.right = parent;
            return pivot;
        }

        private Node RotateLR(Node parent)//Left right rotation
        {
            Node pivot = parent.left;
            parent.left = RotateRR(pivot);
            return RotateLL(parent);
        }
        private Node RotateRL(Node parent)//Right left rotation
        {
            Node pivot = parent.right;//pivot = Y
            parent.right = RotateLL(pivot);
            return RotateRR(parent);
        }
    }

    class Program
    {
        static void Main(string[] args)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Black;

            AVL tree = new AVL();
            tree.Add(10);
            tree.Add(3);
            tree.Add(15);
            tree.Add(2);
            tree.Add(7);
            tree.Add(12);
            tree.Add(1);
            tree.Add(5);

            tree.DisplayAVL();

            //Konsoldan ağaca eklenecek değer alınır.
            Console.Write("\n\n\nAVL'ye eklenmesini istediğiniz değeri giriniz : ");
            tree.Add(int.Parse(Console.ReadLine()));

            tree.DisplayAVL();

            Console.ReadKey();

        }
    }
}
