import networkx as nx
# çizgeyi oluşturmak ve ilgili işlemleri yapmak için "networkx" adlı kütüphaneyi projeye dahil ettim.
import matplotlib.pyplot as plt
# çizgeyi görselleştirmek için "matplotlib" adlı kütüphanenin "pyplot" adlı modülünü dahil ettim.


def en_kisa_yolu_bul(g, s, t):#En kısa yolu bulan method
    try:
        sp = nx.dijkstra_path(g, source=s, target=t)
        return sp

    except KeyError as e:
        if e == 0:
            print(s + " numaralı düğümden" + t + "numaralı düğüme yol (path) yoktur")


def cizgeyi_ciz(g, sp):
    node_pos = nx.get_node_attributes(g, 'pos')
    # her bir düğümün konumu 'pos' anahtarıyla bir sözlük veri yapısında tutuluyor.
    arc_weight = nx.get_edge_attributes(g, 'weight')
    # her bir kenarın ağırlığı 'weight' anahtarıyla bir sözlük veri yapısında tutuluyor.
    red_edges = list(zip(sp, sp[1:]))
    # en kısa yoldaki kenarların listesi oluşturuluyor.
    edge_col = ['black' if edge not in red_edges else 'red' for edge in g.edges()]
    # kenar en kısa yolda yer alıyorsa kırmızı renkle, yer almıyorsa siyah renkle gösterilecek.
    nx.draw_networkx(g, node_pos, arrows=False, node_size=500, arrowsize=20, font_size=14, width=2)
    # düğümler çizilir.
    nx.draw_networkx_edges(g, node_pos, arrowsize=20, edge_color=edge_col, width=2)  # kenarlar çizilir.
    nx.draw_networkx_edge_labels(g, node_pos, edge_labels=arc_weight, label_pos=0.25)  # kenar etiketleri yazdırılır.
    plt.show()  # çizim ekrana yansıtılır.


def main():
    g = nx.DiGraph()  # bir adet boş yönlü çizge oluşturdum.

    # düğümleri çizgeye eklemenin daha kolay yolu :
    # düğüm numaralarını içeren bir liste oluşturulur. Çizmemiz istenen çizge 5 düğümlüdür.
    list_nodes = [0, 1, 2, 3, 4]
    g.add_nodes_from(
        list_nodes)
    # çoklu düğüm ekleme metodu olan "add_nodes_from" metodu ile "list_nodes" listesini çizgeye ekledim.
    g.nodes()
    # bu aşamada sadece düğümleri içeren bir çizge yapısı oluşturulur.

    # kenarları çizgeye eklemenin daha kolay yolu :
    # örnek olarak (0, 4, 2), 0 düğümünden 4 düğümüne 2 ağırlığında bir kenarı gösterir.
    list_arcs = [(0, 1, 5), (0, 2, 3), (0, 4, 2), (1, 3, 6), (2, 1, 1), (1, 2, 2), (2, 3, 2), (4, 1, 6), (4, 2, 10), (4, 3, 4)]
    g.add_weighted_edges_from(list_arcs)
    g.edges()  # bu aşamada kenarlar da çizge yapısına dahil edilir.
    # Her düğümün koordinatları girilir.
    # Bizim çizgemizde düğümler saat yönünde sıralandığı için buna uygun koordinatlar verilir.

    g.nodes[0]['pos'] = (0, 0)
    g.nodes[1]['pos'] = (2, -2)
    g.nodes[2]['pos'] = (1, -4)
    g.nodes[3]['pos'] = (-1, -4)
    g.nodes[4]['pos'] = (-2, -2)

    cizgeyi_ciz(g, [0,0,0])
    sp = en_kisa_yolu_bul(g, 4, 1)
    print("{} ile {} arasındaki en kısa yol : {}".format(4, 1, sp))
    cizgeyi_ciz(g, sp)
    sp = en_kisa_yolu_bul(g, 4, 2)
    print("{} ile {} arasındaki en kısa yol : {}".format(4, 2, sp))
    cizgeyi_ciz(g, sp)
    sp = en_kisa_yolu_bul(g, 4, 3)
    print("{} ile {} arasındaki en kısa yol : {}".format(4, 3, sp))
    cizgeyi_ciz(g, sp)

    g.remove_node(1)
    g.nodes[0]['pos'] = (0, 0)  # her düğümün koordinatları yeniden girilir.
    g.nodes[2]['pos'] = (0, -4)
    g.nodes[3]['pos'] = (-1, -4)
    g.nodes[4]['pos'] = (-2, -2)
    sp = en_kisa_yolu_bul(g, 4, 3)
    print("\n\n{} nolu düğüm çizgeden çıkarıldıktan sonra : ".format(3))
    print("{} ile {} arasındaki en kısa yol : {}".format(4, 3, sp))
    cizgeyi_ciz(g, sp)
    sp = en_kisa_yolu_bul(g, 4, 2)
    print("{} ile {} arasındaki en kısa yol : {}".format(4, 2, sp))
    cizgeyi_ciz(g, sp)


main()
