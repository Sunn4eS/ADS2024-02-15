package by.it.group351003.alexander_krivov.lesson03;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class A_Huffman {

    // Изучите классы Node InternalNode LeafNode
    abstract class Node implements Comparable<Node> {
        // абстрактный класс элемент дерева
        // (сделан abstract, чтобы нельзя было использовать его напрямую)
        // а только через его версии InternalNode и LeafNode
        private final int frequence; // частота символов

        // Генерация кодов (вызывается на корневом узле
        // один раз в конце, т.е. после построения дерева)
        abstract void fillCodes(String code);

        // конструктор по умолчанию
        private Node(int frequence) {
            this.frequence = frequence;
        }

        // метод нужен для корректной работы узла в приоритетной очереди
        // и для сортировок
        @Override
        public int compareTo(Node o) {
            return Integer.compare(frequence, o.frequence);
        }
    }

    // расширение базового класса до внутреннего узла дерева
    private class InternalNode extends Node {
        // внутренний узел дерева
        Node left; // левый ребенок бинарного дерева
        Node right; // правый ребенок бинарного дерева

        // для этого дерева не существует внутренних узлов без обоих детей
        // поэтому вот такого конструктора будет достаточно
        InternalNode(Node left, Node right) {
            super(left.frequence + right.frequence);
            this.left = left;
            this.right = right;
        }

        @Override
        void fillCodes(String code) {
            left.fillCodes(code + "0");
            right.fillCodes(code + "1");
        }
    }

    // расширение базового класса до листа дерева
    private class LeafNode extends Node {
        // лист
        char symbol; // символы хранятся только в листах

        LeafNode(int frequence, char symbol) {
            super(frequence);
            this.symbol = symbol;
        }

        @Override
        void fillCodes(String code) {
            // добрались до листа, значит рекурсия закончена, код уже готов
            // и можно запомнить его в индексе для поиска кода по символу.
            codes.put(this.symbol, code);
        }
    }

    // индекс данных из листьев
    static private Map<Character, String> codes = new TreeMap<>();

    //!!!!!!!!!!!!!!!!!!!!!!!!! НАЧАЛО ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!
    String encode(File file) throws FileNotFoundException {
        // прочитаем строку для кодирования из тестового файла
        Scanner scanner = new Scanner(file);
        String s = scanner.next();

        Map<Character, Integer> count = new HashMap<>();
        // 1. переберем все символы по очереди и рассчитаем их частоту в Map count
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        // 2. перенесем все символы в приоритетную очередь в виде листьев
        for (Map.Entry<Character, Integer> entry : count.entrySet()) {
            priorityQueue.add(new LeafNode(entry.getValue(), entry.getKey()));
        }

        // 3. вынимая по два узла из очереди (для сборки родителя)
        // и возвращая этого родителя обратно в очередь
        // построим дерево кодирования Хаффмана.
        // У родителя частоты детей складываются.
        while (priorityQueue.size() > 1) {
            Node left = priorityQueue.poll();
            Node right = priorityQueue.poll();
            priorityQueue.add(new InternalNode(left, right));
        }

        // 4. последний из родителей будет корнем этого дерева
        // это будет последний и единственный элемент оставшийся в очереди priorityQueue.
        Node root = priorityQueue.peek();
        // 5. заполним коды символов, вызвав fillCodes на корневом узле
        root.fillCodes("");

        StringBuilder sb = new StringBuilder();
        // 6. закодируем исходную строку, подставляя коды символов из Map codes
        for (char c : s.toCharArray()) {
            sb.append(codes.get(c));
        }

        return sb.toString();
    }
    //!!!!!!!!!!!!!!!!!!!!!!!!! КОНЕЦ ЗАДАЧИ !!!!!!!!!!!!!!!!!!!!!!!!!

    public static void main(String[] args) throws FileNotFoundException {
        String root = System.getProperty("user.dir") + "/src/";
        File f = new File(root + "by/it/a_khmelev/lesson03/dataHuffman.txt");
        A_Huffman instance = new A_Huffman();
        long startTime = System.currentTimeMillis();
        String result = instance.encode(f);
        long finishTime = System.currentTimeMillis();
        System.out.printf("%d %d\n", codes.size(), result.length());
        for (Map.Entry<Character, String> entry : codes.entrySet()) {
            System.out.printf("%s: %s\n", entry.getKey(), entry.getValue());
        }
        System.out.println(result);
    }
}
