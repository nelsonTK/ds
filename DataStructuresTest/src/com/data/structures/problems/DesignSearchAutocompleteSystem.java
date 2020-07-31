package com.data.structures.problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

import com.data.structures.problems.ds.LeetCodeExercise;

public class DesignSearchAutocompleteSystem extends LeetCodeExercise {
	static DesignSearchAutocompleteSystem d = new DesignSearchAutocompleteSystem();
	public static void main(String[] args) {

		//Your AutocompleteSystem object will be instantiated and called as such:
		//		String []  sentences = {"i love you","island","iroman","i love leetcode"};
		//		int [] times = stringToArray("[5,3,2,2]");
		String []  sentences = {"uqpewwnxyqxxlhiptuzevjxbwedbaozz","ewftoujyxdgjtazppyztom","pvyqceqrdrxottnukgbdfcr","qtdkgdbcyozhllfycfjhdsdnuhycqcofaojknuqqnozltrjcabyxrdqwrxvqrztkcxpenbbtnnnkfhmebj","jwfbusbwahyugiaiazysqbxkwgcawpniptbtmhqyrlxdwxxwhtumglihrgizrczv","cfptjitfzdcrhw","aitqgitjgrcbacgnaasvbouqsqcwbyskkpsnigtfeecmlkcjbgduban","utsqkmiqqgglufourfdpgdmrkbippffacwvtkpflzrvdlkdxykfpkoqcb","ethtbdopotpamvrwuomlpahtveyw","jiaqkaxovsqtkpdjfbkajpvpyetuoqwnrnpjdhoojbsdvneecsdvgqpyurmsvcy","j","btbnuplyeuccjbernsfbnveillrwdbqledwvpmvdbcugkurrkabtpykhlcogeszclyfuquafouv","hndjzblegevtfkgbjttektox","gtvnlninpvenapyfgmsjdisfnmiktitrutctawosjflvzfkbegnprixzqwzcyhoovsivuwmofsveqkyosowuyamuvy","sawrirvrfrbfagreahrioaombukmdwztbpggnxd","mgdcwptvbvhzyvvumvbjjn","otjvvkegwleyyxtghwgfmlsqlhrlibdvqfinyyebotjpwoaejhtornfgikmifdmwswbqgwhcbzuhrpajxuqicegcptszct","zlondsttyvnnnnxjtoqnlktitwzurissczzbyfsbgpoawodwjpsmavaugnhqtsbeixwl","yehvdehbtmwqkmcjmvpivfzqvevkotwzvjoyfvp","bjximtpayjdcxbrnksbtfnpynzaygygdflowewprqngdadzdhxcpgapjejojrkzrutgcsfpfvpluagniqimfqddldxqiw","bysyrxfykivyauysytgxfhqcrxliulahuizjvozpywrokxujhzpauxwufcxiitukljiiclatfrspqcljjoxpxziumstnhqr","uxtvutlgqapyfltiulwrplesmtowzoyhhjhzihatpuvmutxqgxfawpwypedbz","jzgsdjdawrqfladolduldhpdpagmvllvzamypuqlrpbmhxxadqaqrqavtxeghcyysjynovkiyjtvdluttodtmtocajgttmv","mbijfkmepalhdiubposdksdmmttxblkodcdrxbnxaqebnwliatnxpwaohbwkidia","ljggggbyxwrwanhjonoramexdmgjigrtpz","cqfvkutpipxjepfgsufonvjtotwfxyn","kvseesjazssavispavchdpzvdhibptowhyrrshyntpwkez","nveuzbaosuayteiozmnelxlwkrrrjlwvhejxhupvchfwmvnqukphgoacnazuoimcliubvhv","uwrpwhfdrxfnarxqpkhrylkwiuhzubjfk","bniyggdcloefwy","ihranmhbsahqjxesbtmdkjfsupzdzjvdfovgbtwhqfjdddwhdvrnlyscvqlnqpzegnvvzyymrajvso","lscreasfuxpdxsiinymuzybjexkpfjiplevqcjxlm","uwgnfozopsygnmptdtmmuumahoungpkodwxrcvfymqpeymaqruayvqqgoddgbnhemtsjifhxwiehncswxzrghf","nyfbxgcpfrzyqwfjzgmhuohjhrjizsyjqgmertmooeiaadcmiuyyylpcosnweoyydeauazhzbeaqn","tpylrxbwnkrfxckfdlvrbytaezuzmyscpvruthuvbxjenkeolvqsrjqzizyclzmqtjvnamdansmzyspcfghfprorqprua","nhldlmxpuckxeekipkrzugatjiivtazjbjyxokksyueyjbgmrovbckbxqcqefaiavzsarbbypgmpxe","sylraqsd","xr","xkzpxkhrucyatpatkigvntohihibyisyqtkjdhatdvyvxbjttz","nvnz","blzddwxphkgqfsfzfclwytstpvpzgcdeggdwzukzirscfzcteeuqbmmrfxcnokbbyxkqrxtjfarcefiynwfmy","inuxmuhtdwpuvyludwtokhtalxbuccepsayrjycbcwbtnfholjvkmypodv","awwillrm","xznodxngrstjrwqzmlmigpw","khlxjdtictufdfbkgfusdtaaeuspbbfmtjodflgqofzlqnulkdztflm","nlngmckslyqzjiyiexbropbxnynjcstziluewypboqhqndqsxhtnosrgrameajovsclrgwqjgnztvxrkhwnxkfrf","yroadxhxyacaexrwju","ujxlbpcbxdqrvubifnpzjmmkolyljzjhdegaiowaal","tnfnjgtxbckbpyplucprxcqzhrfjimylmlhdglntfydepltjvklyxesndzuubienhvuaqc","ouedhtkpkg","ygchsrrubucqffewifsxaefwocfaiiupqbomktvrcddggqfgnaycstpccwtbheyaqwhosxajqeqqxzyjsfng","jqqgpjvfkgjh","csowoazaiyejgyixszqmtctpzlkccccqregyhtvxccvrpkupwcyhqatxscevzdfbdqnuyadiyfnhysddfyxpgqtjiogmxsmzbbkr","dlzxdpchkdaztkqtrjmuujgoiae","plcjkwukkyqluxjbhxsyeaqvviinfuujsafwsquidvmutsrukxwrv","yopqbtpoqhpcktjangauzcvvpephhprpaaclbbkgchlqkrwdsaupeizlwxzcpkchoagmrrkwdkthosmrjefgbumnrjsb"};
		int [] times = stringToArray("[12,9,4,4,1,5,3,4,7,9,2,4,2,3,11,13,1,3,4,10,7,1,9,5,10,14,5,3,2,11,5,14,4,13,11,5,15,8,1,12,2,11,4,2,11,14,9,12,1,7,13,11,7,2,6,10]");

		AutocompleteSystem obj = d.new AutocompleteSystem(sentences, times);
		List<String> param_1 = obj.input('s');
		AutocompleteSystemUnofficialSolution1 a1 = new AutocompleteSystemUnofficialSolution1(sentences, times);

		param_1 = a1.input('s');
		for (String s : param_1) {
			System.out.println(s);
		}


	}


	/**
	 * @intuition
	 * 		The goal here was to create a Trie that can save the state of the searches 
	 * 		
	 * 		so that we don't do the search always from the begining
	 * 
	 * 		we use as state, a variable currentSearch and a node state
	 * 
	 * 		I use the priorityqueue to sort the data and keep the space complexity as short as possible
	 * 
	 * @score
	 * 		Runtime: 173 ms, faster than 63.47% of Java online submissions for Design Search Autocomplete System.
	 *		Memory Usage: 48.7 MB, less than 73.11% of Java online submissions for Design Search Autocomplete System.
	 * 
	 * @fail
	 * 		1) forgot to reset currentSearch
	 * 		2) failed order, I was returning lexico graphically first
	 * 		3) forgot to return the value in the non "#" case
	 * 		4) sort order was reverse for times
	 * 		5) comparator was alright the error was also in the if conditional before the add
	 * 		6) its wrong but can't figured out why: testcase https://leetcode.com/submissions/detail/371460897/testcase/
	 *  	7) forgot to update the number of times a sentence was accessed
	 *   	8) error in the comparision that decides whether or not to update or add a new word before "#"
	 * 
	 * @Optimizations
	 * 		I Could have used an array instead of an array instead of a hashmap, but I would need to do the reply in another way
	 * 
	 * 		probably having some kind of cache of the sentences for each node
	 * 
	 * @time
	 * 		O(N*L) => O(N*LogK*L) => O(NLog3 * L) Where 
	 * 			N is the number of sentences, 
	 * 			L is the length of the words and 
	 * 			K is the size of the priorityqueue   as K is always 3
	 * 
	 * @space
	 * 		O(L*N) words save in the string
	 * 			N is number of sentences
	 * 			L is size
	 * 
	 * @author Nelson Costa
	 *
	 */
	class AutocompleteSystem {
		T trie;
		String currentSearch;

		public AutocompleteSystem(String[] sentences, int[] times) {
			//create tree
			//duplicates?
			trie = new T();
			Set<String> duplicates  = new HashSet<>();
			currentSearch = "";

			for (int i = 0; i < sentences.length; i++)
			{
				if (!duplicates.contains(sentences[i]))
				{  
					trie.add(sentences[i], times[i]);
					duplicates.add(sentences[i]);
				}
			}
		}

		/**
		 * 
		 * @param c
		 * @return
		 */
		public List<String> input(char c) {


			if (c == '#')
			{
				//Save new word or update frequency

				TN currentSentence = trie.getNode(currentSearch);

				if (currentSentence != null && currentSentence.isFinal)
				{ 

					currentSentence.times++;
				}
				else
				{

					trie.add(currentSearch, 1);
				}

				//reset state

				trie.state = null;
				currentSearch = "";

				return new ArrayList<String>();
			}
			else
			{
				currentSearch += c + "";
				return trie.getStartsWith(currentSearch);
			}
		}

		class T {
			TN root;
			TN state;
			static final int TOP = 3;

			public T(){
				root = new TN();
				state = null;
			}

			public void add(String s, int times)
			{
				TN node = root;


				for(char c : s.toCharArray())
				{
					TN child = node.getChild(c);

					if (child == null)
					{
						child = new TN(c);
						node.addChild(child);
					}

					node = child;
				}


				node.str = s;
				node.times = times;
				node.isFinal = true;

			}

			public List<String> getStartsWith(String s)
			{

				//check for the state
				if (state == null)
				{
					state = getNode(s);

				}
				else
				{
					//if we already had a state than we just need to go one child forward
					state = state.getChild(s.charAt(s.length() - 1));
				}

				//if cant find state return empty
				if(state == null)
					return new ArrayList<String>();


				PriorityQueue<TN> pq = new PriorityQueue<>(
						(a, b) -> 
						{
							if (Integer.compare(a.times, b.times) == 0)
							{
								return b.str.compareTo(a.str);
							}
							else
							{
								return Integer.compare(a.times, b.times);
							}
						}
						);

				//Search for top 3 words, O(NLogK * L) => O(27 * NLogK * L)  
				search(pq, state);

				List<String> list = new ArrayList<String>();

				while (!pq.isEmpty())
				{
					list.add(pq.poll().str);
				}

				Collections.reverse(list);
				return list;
			}

			private void search(PriorityQueue<TN> ans, TN node)
			{
				//keep the size of the max priotity under TOP variable
				if (node.isFinal) //O(NLogK)
				{
					if (ans.size() < TOP)
					{
						ans.add(node); 
					}
					else
					{
						ans.add(node);
						ans.poll();
					}
				}

				//search for children
				for (char c : node.children.keySet()) //O(27)
				{
					search(ans, node.getChild(c));
				}

			}

			//O(L)
			public TN getNode(String s){
				TN node = root;

				for(char c : s.toCharArray())
				{
					node = node.getChild(c);

					if (node == null)
					{
						return null;
					}
				}
				return node;
			}

		}

		class TN 
		{
			String str;
			int times;
			char val;
			boolean isFinal;
			HashMap<Character, TN> children = new HashMap<>();

			public TN ()
			{
			}
			public TN (char c)
			{
				val = c;
			}

			public TN getChild(char c){
				return children.get(c);
			}

			public void addChild(TN c){
				children.put(c.val, c);
			}
		}
	}
}


/*********************
 * OTHERS SOLUTIONS
 *********************/
/**
 * Solution 3
 * 
 * @score
 * 		Runtime: 200 ms, faster than 51.09% of Java online submissions for Design Search Autocomplete System.
		Memory Usage: 48.8 MB, less than 65.53% of Java online submissions for Design Search Autocomplete System.
 * 
 * @time
 * 		O(k*l)
 * 		k size
 * 		l sentences
 * 
 * @author Nelson Costa
 *
 */
class Node {
	String sentence;
	int times;

	Node(String st, int t) {
		sentence = st;
		times = t;
	}
}

class Trie {
	int times;
	Trie[] branches = new Trie[27];
}

class AutocompleteSystemSolution3 {
	private Trie root;
	private String cur_sent = "";

	public AutocompleteSystemSolution3(String[] sentences, int[] times) {
		root = new Trie();
		for (int i = 0; i < sentences.length; i++) {
			insert(root, sentences[i], times[i]);
		}
	}

	private int toInt(char c) {
		return c == ' ' ? 26 : c - 'a';
	}

	private void insert(Trie t, String s, int times) {
		for (int i = 0; i < s.length(); i++) {
			if (t.branches[toInt(s.charAt(i))] == null) {
				t.branches[toInt(s.charAt(i))] = new Trie();
			}
			t = t.branches[toInt(s.charAt(i))];
		}
		t.times += times;
	}

	private List<Node> lookup(Trie t, String s) {
		List<Node> list = new ArrayList<>();
		for (int i = 0; i < s.length(); i++) {
			if (t.branches[toInt(s.charAt(i))] == null) {
				return new ArrayList<>();
			}
			t = t.branches[toInt(s.charAt(i))];
		}
		traverse(s, t, list);
		return list;
	}

	private void traverse(String s, Trie t, List<Node> list) {
		if (t.times > 0) list.add(new Node(s, t.times));
		for (char i = 'a'; i <= 'z'; i++) {
			if (t.branches[i - 'a'] != null) {
				traverse(s + i, t.branches[i - 'a'], list);
			}
		}
		if (t.branches[26] != null) {
			traverse(s + ' ', t.branches[26], list);
		}
	}

	public List<String> input(char c) {
		List<String> res = new ArrayList<>();
		if (c == '#') {
			insert(root, cur_sent, 1);
			cur_sent = "";
		} else {
			cur_sent += c;
			List<Node> list = lookup(root, cur_sent);
			Collections.sort(
					list,
					(a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
			for (int i = 0; i < Math.min(3, list.size()); i++) res.add(list.get(i).sentence);
		}
		return res;
	}
}   

/**
 * 
 * @intuition
 * 
 * 	This solution is also lovely
 * 
 * 	What this man does is add to each node the search index of the sentences
 * 
 * 	He also mantains a map with the count of words and times.
 * 
 * 	when the user puts an input he searchs for the node, than the node has all the indexes of the sentences
 * 
 * 	and therefore he just needs to sort them according to times, and then lexicographically
 * 
 *	again its a cool solution, but I decided to move away from having the nodes with references to all sentences.
 * 
 * 	Was a design decision, but I like this solution too.
 * 
 * @source
 * 		https://discuss.leetcode.com/topic/110575/java-trie-solution-avoiding-traversal-from-root-and-subtree
 * 
 * @author Nelson Costa
 *
 */
class AutocompleteSystemUnofficialSolution1 {	
    
    private static class TrieNode {
        private static final int MAX_LINKS = 27;

        private TrieNode[] links;

        private boolean end;

        private Set<Integer> sentenceIndices;

        public TrieNode() {
            links = new TrieNode[MAX_LINKS];
            sentenceIndices = new HashSet<>();
        }

        public boolean containsKey(final char ch) {
            return links[charIndex(ch)] != null;
        }

        public TrieNode get(final char ch) {
            return links[charIndex(ch)];
        }

        public void put(final char ch, final TrieNode node) {
            links[charIndex(ch)] = node;
        }

        public boolean isEnd() {
            return end;
        }

        public void setEnd() {
            this.end = true;
        }

        public void addSentenceIndex(final int index) {
            if (!sentenceIndices.contains(index)) {
                sentenceIndices.add(index);
            }
        }

        private int charIndex(final char ch) {
            return ch != ' ' ? ch - 'a' : links.length-1;//links[26] will represent space character
        }
    }

    private List<String> sentences;
    private Map<String, Integer> sentenceCountMap;

    private TrieNode root;
    private StringBuffer buffer;
    private TrieNode currentNode;

    public AutocompleteSystemUnofficialSolution1(final String[] sentences, final int[] times) {
        this.sentences = new ArrayList<>(Arrays.asList(sentences));
        this.sentenceCountMap = new HashMap<>();
        for (int i = 0; i < sentences.length; i++) {
            sentenceCountMap.put(sentences[i], times[i]);
        }

        this.root = new TrieNode();
        this.buffer = new StringBuffer();
        this.currentNode = root;

        for (int i = 0; i < sentences.length; i++) {
            insert(i);
        }
    }

    public List<String> input(final char c) {
        if (c == '#') {
            String sentence = this.buffer.toString();
            this.buffer.setLength(0);
            this.currentNode = root;

            if (!sentenceCountMap.containsKey(sentence)) {
                sentences.add(sentence);
                insert(sentences.size()-1);
            }
            sentenceCountMap.put(sentence, sentenceCountMap.getOrDefault(sentence, 0) + 1);
            return Collections.emptyList();
        } else {
            this.buffer.append(c);

            if (currentNode == null) {// no more matching is possible
                return Collections.emptyList();
            } else if (!currentNode.containsKey(c)) {// no more matching is possible
                currentNode = null;
                return Collections.emptyList();
            } else {
                currentNode = currentNode.get(c);

                List<Integer> candidateIndices = new ArrayList<>();
                candidateIndices.addAll(currentNode.sentenceIndices);
                Collections.sort(candidateIndices, (index1, index2) -> {
                    String sentence1 = sentences.get(index1);
                    String sentence2 = sentences.get(index2);
                    int count1 = sentenceCountMap.get(sentence1);
                    int count2 = sentenceCountMap.get(sentence2);
                    return count1 == count2 ? sentence1.compareTo(sentence2) : Integer.compare(count2, count1);
                });

                final List<Integer> resultIndices = candidateIndices.subList(0, Math.min(3, candidateIndices.size()));
                return resultIndices.stream().map(index -> this.sentences.get(index)).collect(Collectors.toList());
            }
        }
    }

    private void insert(final int sentenceIndex) {
        TrieNode current = root;
        String sentence = sentences.get(sentenceIndex);
        for (char ch : sentence.toCharArray()) {
            if (!current.containsKey(ch)) {
                current.put(ch, new TrieNode());
            }
            current = current.get(ch);
            current.addSentenceIndex(sentenceIndex);
        }
        current.setEnd();
    }
}

/**
 * Top Solution
 * 
 * for each node it is mantained a hotlist of sentences. the hotlist hash somekind of priority queue behavior
 * 
 * @author Nelson Costa
 *
 */
class AutocompleteSystemUnofficialSolution2 {
    class TrieNode {
       private TrieNode[] children;
       private ArrayList<String> hotlist;
        
       public TrieNode() {
           children = new TrieNode[27]; //26 + space
           hotlist = new ArrayList<String>();
       }
        
                // Insert this string s into the Trie tree
        private void update(String s) {
            TrieNode curr = root;
            for(int i=0; i<s.length(); i++) {
                int idx = (s.charAt(i) == ' ') ? 26 : (s.charAt(i) - 'a');
                if(curr.children[idx] == null) {
                    curr.children[idx] = new TrieNode();
                }
                curr = curr.children[idx];
                insert(curr.hotlist, s);
            }
        }

        // Insert the string s into the sorted list and keep the size of list as 3
        private void insert(ArrayList<String> hotlist, String s) {
            int i;
            for(i=0; i<hotlist.size(); i++) {
                if(hotlist.get(i).equals(s)) {
                    hotlist.remove(i);
                    break;
                }
            }
            for(i=0; i<hotlist.size(); i++) {
                if(greater(s, hotlist.get(i))) {
                    hotlist.add(i, s);
                    break;
                }
            }
            if(i == hotlist.size()) {
                hotlist.add(s);
            }
            if(hotlist.size() > 3) {
                hotlist.remove(hotlist.size() - 1);
            }
        }

        // 
        private boolean greater(String a, String b) {
            int cntA = count.get(a);
            int cntB = count.get(b);
            if(cntA != cntB) {
                return cntA > cntB;
            }
            return a.compareTo(b) < 1 ? true : false;
        }
    }
    
    private TrieNode root;
    private TrieNode curr;  // curr is keep track current node search until '#' reset it
    private StringBuilder sb;
    private HashMap<String, Integer> count;
    
    public AutocompleteSystemUnofficialSolution2(String[] sentences, int[] times) {
        root = new TrieNode();
        curr = root;
        sb = new StringBuilder();
        count = new HashMap<String, Integer>();
        
        for(int i=0; i<sentences.length; i++) {
            count.put(sentences[i], times[i]);
            root.update(sentences[i]);
        }
    }
    
    public List<String> input(char c) {
        if(c == '#') {
            curr = root;
            String s = sb.toString();
            sb = new StringBuilder();
            count.put(s, count.getOrDefault(s, 0) + 1);
            curr.update(s);
            return new ArrayList<String>();
        } else {
            sb.append(c);
            if(curr == null) {
                return new ArrayList<String>();
            }
            int idx = (c == ' ') ? 26 : (c - 'a');
            curr = curr.children[idx];
            if(curr == null) {
                return new ArrayList<String>();
            }
            return curr.hotlist;
        }
    }
}