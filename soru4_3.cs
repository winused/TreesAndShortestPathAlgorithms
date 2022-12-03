using System;
using System.Collections.Generic;

namespace DS_proje4_3
{
    public class PrimMinimumSpanningTree
    {
        int koseSayisi; // çizgedeki köşe sayısı

        public PrimMinimumSpanningTree(int k)
        {
            this.koseSayisi = k;  // MST objesi agacın köşe sayısını argüman alarak oluşturuluyor.
        }

        public int minDeger(int[] key, bool[] mstSet) // Henüz ağaca dahil edilmemiş köşelerden minimum değerlikli olanı bulan metot
        {
            int min = int.MaxValue, min_index = -1;

            for (int v = 0; v < koseSayisi; v++)
                if (mstSet[v] == false && key[v] < min)
                {
                    min = key[v];
                    min_index = v;
                }

            return min_index;
        }

        public void yazdir(int[] mstDizisi, int[,] matris) // MST'yi yazdırmaya yarayan metot. MST ögelerini tutan dizi ve 2 boyutlu komşuluk matrisi argüman olarak gönderilir. 
        {
            Console.WriteLine("Kenar \tAğırlık");
            for (int i = 1; i < koseSayisi; i++)
                Console.WriteLine(mstDizisi[i] + " - " + i + "\t" + matris[i, mstDizisi[i]]);
        }

        public void FindMST(int[,] matris) // çizgeyi iki boyutlu komşuluk matrisi şeklinde argüman olarak alan, MST'yi bulan ve yazdıran metot
        {

            int[] parent = new int[koseSayisi]; //oluşturulacak MST'yi tutacak olan dizi. Boyutu köşe sayısı kadardır, çünkü MST'nin bütün köşeleri içermesi gerekir.

            int[] key = new int[koseSayisi]; // minimum ağırlıklı kenarların değerlerini tutan dizi

            bool[] mstSet = new bool[koseSayisi]; // algoritma işletimi esnasında uğranmış köşelerin bilgilerini tutan boolean dizisi.

            for (int i = 0; i < koseSayisi; i++)
            {
                key[i] = int.MaxValue;
                mstSet[i] = false;
            }

            // ilk düğüm MST'nin kökü olarak kabul edilir ve 0 değeri atanır
            key[0] = 0;
            parent[0] = -1;

            for (int count = 0; count < koseSayisi - 1; count++)
            {
                int u = minDeger(key, mstSet);

                mstSet[u] = true;

                for (int v = 0; v < koseSayisi; v++)

                    if (matris[u, v] != 0 && mstSet[v] == false && matris[u, v] < key[v])
                    {
                        parent[v] = u;
                        key[v] = matris[u, v];
                    }
            }

            yazdir(parent, matris);
        }
    }

    public class DijkstraShortestPath
    {
        public DijkstraShortestPath() { }

        public int Distance(int[] distance, bool[] shortestPathTreeSet, int verticesCount)
        {
            int min = int.MaxValue;
            int minIndex = 0;

            for (int v = 0; v < verticesCount; ++v)
            {
                if (shortestPathTreeSet[v] == false && distance[v] <= min)
                {
                    min = distance[v];
                    minIndex = v;
                }
            }

            return minIndex;
        }

        public void Find(int[,] graph, int source, int verticesCount)
        {
            int[] distance = new int[verticesCount];
            bool[] shortestPathTreeSet = new bool[verticesCount];

            for (int i = 0; i < verticesCount; ++i)
            {
                distance[i] = int.MaxValue;
                shortestPathTreeSet[i] = false;
            }

            distance[source] = 0;

            for (int count = 0; count < verticesCount - 1; ++count)
            {
                int u = Distance(distance, shortestPathTreeSet, verticesCount);
                shortestPathTreeSet[u] = true;

                for (int v = 0; v < verticesCount; ++v)
                    if (!shortestPathTreeSet[v] && Convert.ToBoolean(graph[u, v]) && distance[u] != int.MaxValue && distance[u] + graph[u, v] < distance[v])
                        distance[v] = distance[u] + graph[u, v];
            }

            Console.WriteLine("Köşe    {0} Nolu Köşeye Olan En Kısa Mesafesi", source);
            for (int i = 0; i < verticesCount; ++i)
                Console.WriteLine("{0}\t  {1}", i, distance[i]);
        }
    }

    public class Cizge
    {
        public int koseSayisi;
        public List<int>[] komsulukDizisi;

        public Cizge(int k)
        {
            this.koseSayisi = k;
            komsulukDizisi = new List<int>[k];
            for (int i = 0; i < koseSayisi; ++i)
                komsulukDizisi[i] = new List<int>();
        }

        public void AddEdge(int v, int w)
        {
            komsulukDizisi[v].Add(w);
        }

        void DFTUtil(int v, bool[] visited)
        {
            visited[v] = true;
            Console.Write(v + " ");

            List<int> vList = komsulukDizisi[v];
            foreach (var n in vList)
            {
                if (!visited[n])
                    DFTUtil(n, visited);
            }
        }

        public void DFT(int v)
        {
            bool[] visited = new bool[koseSayisi];

            DFTUtil(v, visited);
        }
    }
    class Program
    {
        static void Main(string[] args)
        {
            Console.BackgroundColor = ConsoleColor.White;
            Console.Clear();
            Console.ForegroundColor = ConsoleColor.Black;

            //Dijkstra Shortest Path classı açılır ve matris gönderilir.
            //Find methodu çalıştırılarak dijkstra en kısa yol bulunur.

            int[,] matris =  {
                          { 0, 6, 0, 0, 0, 0, 0, 9 },//0
                          { 6, 0, 9, 0, 0, 0, 0, 11 },//1
                          { 0, 9, 0, 5, 0, 6, 0, 0 },//2
                          { 0, 0, 5, 0, 9, 0, 16, 0 },//3
                          { 0, 0, 0, 9, 0, 10, 0, 0 },//4
                          { 0, 0, 6, 0, 10, 0, 2, 0 },//5
                          { 0, 0, 0, 16, 0, 2, 0, 1 },//6
                          { 9, 11, 0, 0, 0, 0, 1, 0 }//7
                            };

            DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath();

            Console.WriteLine("Dijkstra Shortest Path:");
            dijkstraShortestPath.Find(matris, 2, 8);

            Console.WriteLine();

            //Prim Minimum spanning tree classı açılır ve matris FindMST methoduna gönderilir


            matris = new int[,] { { 0, 2, 0, 6, 0 },
                                  { 2, 0, 3, 8, 5 },
                                  { 0, 3, 0, 0, 7 },
                                  { 6, 8, 0, 0, 9 },
                                  { 0, 5, 7, 9, 0 } };

            PrimMinimumSpanningTree primMinimumSpanningTree = new PrimMinimumSpanningTree(5);
            Console.WriteLine("Prim Minimum Spanning Tree:");
            primMinimumSpanningTree.FindMST(matris);
            Console.WriteLine();

            //DFT methodu olan çizge classı açılır ve veriler bu classa gönderilir.
            //Seçilen köşeden başlanarak çizge dolaşılır. (Seçilen köşe değiştirilebilir.
            Cizge mCizge = new Cizge(4);

            mCizge.AddEdge(0, 1);
            mCizge.AddEdge(0, 2);
            mCizge.AddEdge(1, 2);
            mCizge.AddEdge(2, 0);
            mCizge.AddEdge(2, 3);
            mCizge.AddEdge(3, 3);
            Console.WriteLine("Depth First Traverse:");
            Console.WriteLine("2 Nolu Köşeden Başlayarak Derinlik Öncelikli Dolaşma : ");

            mCizge.DFT(2);

            Console.ReadKey();

        }
    }
}
