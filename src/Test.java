import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Test {

	private static Scanner sc;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n, m; // n dinh, m canh
		char s, e; // dinh dau s, dinh cuoi e
		s = 'B';
		e = 'L';
		List<String> list = new ArrayList<String>();

		try {
			sc = new Scanner(new File("input6.txt"));
			n = sc.nextInt();
			m = sc.nextInt();
			System.out.println(n + " " + m);
			String dt = sc.nextLine();
			for (int i = 0; i < m; i++) {
				dt = sc.nextLine();
				list.add(dt);
				System.out.println(dt);
			}
		} catch (FileNotFoundException er) {
			// TODO Auto-generated catch block
			er.printStackTrace();
		}

		System.out.println("\n\nBFS");
		BFS(list, s, e);

		System.out.println("\n\nDFS");
		DFS(list, s, e);

	}

	public static void BFS(List<String> list, char s, char e) {
		List<Character> phat_trien_tt = new ArrayList<Character>();
		List<String> trang_thai = new ArrayList<String>();
		List<Character> danh_sach = new ArrayList<Character>();
		Queue<Character> queue = new LinkedList<Character>();
		queue.add(s);
		int buoc = 1;

		while (queue.size() > 0) {
			char c = queue.poll();
			
			//xét các đỉnh đã duyệt
			if (phat_trien_tt.contains(c)) {
				continue;
			} else {
				phat_trien_tt.add(c);
			}
			
			
			//không xét các đỉnh đã duyệt
//			phat_trien_tt.add(c);
			
			
			System.out.printf("\nBuoc " + buoc + "\t" + c + ":\t");
			buoc++;
			if (c == e) {
				System.out.printf("TTKT");
				break;
			}
			Queue<Character> queue2 = new PriorityQueue<Character>();
			String tt = "";
			for (String str : list) {
				if (str.charAt(0) == c) {
					tt += str.charAt(2);
					queue2.add(str.charAt(2));
					System.out.printf(str.charAt(2) + " ");
				}
			}

			trang_thai.add(tt);
			tt = "";

			System.out.printf("\t");
			Object[] arr = queue.toArray();
			for (Object character : arr) {
				danh_sach.add((Character) character);
			}
			while (queue2.size() > 0) {
				danh_sach.add(queue2.peek());
				queue.add(queue2.poll());
			}
			for (Character character : danh_sach) {
				System.out.printf(character + " ");
			}
			danh_sach.removeAll(danh_sach);
		}

		Stack<Character> stack = new Stack<Character>();
		stack.push(e);
		while (e != s) {
			for (int i = trang_thai.size() - 1; i >= 0; i--) {
				int index = trang_thai.get(i).indexOf(e + "");
				if (index >= 0) {
					e = phat_trien_tt.get(i);
					stack.push(e);
//					System.out.println(e);
				}
			}
		}
		System.out.println("\n\nĐường đi: ");
		while (stack.size() > 0) {
			System.out.printf(stack.pop() + "->");
		}
	}

	public static void DFS(List<String> list, char s, char e) {
		List<Character> phat_trien_tt = new ArrayList<Character>();
		List<String> trang_thai = new ArrayList<String>();
		List<Character> danh_sach = new ArrayList<Character>();
		Stack<Character> stack = new Stack<Character>();
		stack.push(s);
		int buoc = 1;

		while (stack.size() > 0) {
			char c = stack.pop();
			
			//xét các đỉnh đã duyệt
			if (phat_trien_tt.contains(c)) {
				continue;
			} else {
				phat_trien_tt.add(c);
			}
			
			//không xét các đỉnh đã duyệt
//			phat_trien_tt.add(c);
			
			
			System.out.printf("\nBuoc " + buoc + "\t" + c + ":\t");
			buoc++;
			if (c == e) {
				System.out.printf("TTKT");
				break;
			}
			Queue<Character> queue2 = new PriorityQueue<Character>();
			String tt = "";
			for (String str : list) {
				if (str.charAt(0) == c) {
					tt += str.charAt(2);
					queue2.add(str.charAt(2));
					System.out.printf(str.charAt(2) + " ");
				}
			}

			trang_thai.add(tt);
			tt = "";

			System.out.printf("\t");
			Object[] arr = stack.toArray();
			for (Object character : arr) {
				danh_sach.add((Character) character);
			}
			while (queue2.size() > 0) {
				danh_sach.add(queue2.peek());
				stack.push(queue2.poll());
			}
			for (Character character : danh_sach) {
				System.out.printf(character + " ");
			}
			danh_sach.removeAll(danh_sach);
		}
		stack.removeAll(stack);
		stack.push(e);
		while (e != s) {
			for (int i = trang_thai.size() - 1; i >= 0; i--) {
				int index = trang_thai.get(i).indexOf(e + "");
				if (index >= 0) {
					e = phat_trien_tt.get(i);
					stack.push(e);
//					System.out.println(e);
				}
			}
		}
		System.out.println("\n\nĐường đi: ");
		while (stack.size() > 0) {
			System.out.printf(stack.pop() + "->");
		}
	}

}
