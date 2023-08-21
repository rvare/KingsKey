/* This file is part of Kings Key project
 * Copyright (C) 2023 Richard E. Varela (rvare) and Henry Kong.
 * 
 * Kings Key is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Kings Key is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR PARTICULAR PURPOSE. See GNU General Public Licnese for more details.
 * 
 * You should have recieved a copy of the GNU General Public License along
 * with Kings Key. If not, see <https://www.gnu.org/licenses/>.
 */

package Model.Trie;
/*
 * No trie class exists in the java common libraries so this will be a basic one.
 */
import java.util.HashMap;
import java.util.Map;

public class Trie_Node{
    private boolean Is_Leaf;
    private Map<Character, Trie_Node> Trie_Children;

    // Constructor
    public Trie_Node() {
        Is_Leaf = false;
        Trie_Children = new HashMap<>();
    }

    // Insertion
    public void insert(String Node_Key) {
        //System.out.println("Inserting the key \"" + Node_Key + "\"");

        // root node
        Trie_Node root_node = this;

        for (char character: Node_Key.toCharArray()) {
            // if the path doesn't exist, create a new node.
            root_node.Trie_Children.putIfAbsent(character, new Trie_Node());

            // going to the next node
            root_node = root_node.Trie_Children.get(character);
        }
        root_node.Is_Leaf = true;
    }
    // Searching
    public boolean search(String Node_key) {
        //System.out.print("Searching the key \"" + Node_key + "\" : ");

        Trie_Node Current_Node = this;

        // repeat
        for (char character: Node_key.toCharArray()) {
            // going to the next node
            Current_Node = Current_Node.Trie_Children.get(character);

            if (Current_Node == null) {
                return false;
            }
        }

        return Current_Node.Is_Leaf;
    }
} // End of Trie_Node class

/*class Example {
    public static void main (String[] args) {
        // construct a new Trie node
        Trie_Node New_Trie = new Trie_Node();

        New_Trie.insert("delft");
        New_Trie.insert("delf");
        New_Trie.insert("del");

        System.out.println(New_Trie.search("del"));            // true
        System.out.println(New_Trie.search("delf"));           // true
        System.out.println(New_Trie.search("delft"));          // true
        System.out.println(New_Trie.search("delftstack"));   // false

        New_Trie.insert("delftstack");

        System.out.println(New_Trie.search("del"));            // true
        System.out.println(New_Trie.search("delf"));           // true
        System.out.println(New_Trie.search("delft"));          // true
        System.out.println(New_Trie.search("delftstack"));   // true

    }
}*/