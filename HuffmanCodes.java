package algorithms.greedy;

import org.w3c.dom.Node;

import java.util.*;

public class HuffmanCodes {

    public Map<Character, String> generateHuffmanCodes(Map<Character, Integer> frequencies) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        Map<Character, int[]> charToNodeMap = new HashMap<>();
        int nodeId = 0;

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            int[] node = new int[]{nodeId++, entry.getValue(), -1, -1, entry.getKey()};
            pq.add(node);
            charToNodeMap.put(entry.getKey(), node);
        }

        List<int[]> nodes = new ArrayList<>(pq);

        while (pq.size() > 1) {
            int[] left = pq.poll();
            int[] right = pq.poll();
            assert right != null;
            int[] merged = new int[]{nodeId++, left[1] + right[1], nodes.indexOf(left), nodes.indexOf(right), '\0'};
            pq.add(merged);
            nodes.add(merged);
        }

        int[] root = pq.poll();
        Map<Character, String> huffmanCodes = new HashMap<>();
        assert root != null;
        generateCodes(nodes, root, "", huffmanCodes, charToNodeMap);

        return huffmanCodes;
    }

    private void generateCodes(List<int[]> nodes, int[] node, String code, Map<Character, String> huffmanCodes, Map<Character, int[]> charToNodeMap) {
        if (node[2] == -1 && node[3] == -1) {
            huffmanCodes.put((char) node[4], code);
            return;
        }
        if (node[2] != -1) generateCodes(nodes, nodes.get(node[2]), code + "0", huffmanCodes, charToNodeMap);
        if (node[3] != -1) generateCodes(nodes, nodes.get(node[3]), code + "1", huffmanCodes, charToNodeMap);
    }

    public static void main(String[] args) {
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('a', 5);
        frequencies.put('b', 9);
        frequencies.put('c', 12);
        frequencies.put('d', 13);
        frequencies.put('e', 16);
        frequencies.put('f', 45);

        HuffmanCodes hc = new HuffmanCodes();
        Map<Character, String> huffmanCodes = hc.generateHuffmanCodes(frequencies);

        System.out.println("Huffman Codes:");
        for (Map.Entry<Character, String> entry : huffmanCodes.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public Map<Character, String> buildHuffmanTree(List<Node> nodes) {
        return Map.of();
    }
}
