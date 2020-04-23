/*ID:20132071 DATE:2019-05-11*/
package myPackage;

import java.util.Scanner;

public class TimetableManager
{
	private MovieTimetable[] table;
	private ScreenManager sm;
	private int pos;

	public TimetableManager(int n)
	{
		table = new MovieTimetable[n];
		pos = 0;
	}

	public void add()
	{
		Scanner s = new Scanner(System.in);
		String title, openDate, grade;
		int playtime = 0;
		Screen sc[] = sm.getScreens();
		Movie m = null;
		int select = 0;
		int mArrayCnt = 1;
		title = openDate = grade = null;

		while (true)
		{
			if (sc == null)
			{
				System.out.println("등록된 상영관이 없습니다. 최소한 하나의 상영관을 등록해주세요.");
				return;
			}
			// 처음 등록 시 조건의 if문
			if (pos == 0)
			{
				System.out.println("**현재 등록된 영화가 없습니다.");
			} else
			{
				Movie mArray[] = new Movie[pos];
				for (int i = 0; i < pos; i++)
				{
					if (i == 0)
					{
						mArray[i] = table[i].getMovie();
					}
					if (i > 0)
					{
						if (!table[i - 1].getMovie().getTitle().equals(table[i].getMovie().getTitle()))
						{
							mArray[i] = table[i].getMovie();
							mArrayCnt++;
						}
					}
				}
				for (int i = 0; i < mArrayCnt; i++)
				{
					System.out.println("[" + (i + 1) + "] " + mArray[i].getTitle() + " " + mArray[i].getOpenDate());
				}
				System.out.print("0을 입력하면 새로운 영화 등록 선택번호 입력하면 원하는거 등록 : ");
				select = s.nextInt();
				if (select != 0)
				{
					m = mArray[select - 1];
				}
				// 영화 목록 여기서 출력하고 선택을함. 혹은 0
			}
			// 처음 등록이 아닐 때
			if (select == 0)
			{
				System.out.print("영화제목 : ");
				title = s.next();
				System.out.print("개봉일 : ");
				openDate = s.next();
				System.out.print("상영시간 : ");
				playtime = s.nextInt();
				System.out.print("등급 : ");
				grade = s.next();

				m = new Movie(title, openDate, playtime, grade);
				System.out.println("**신규 영화(" + title + ")가 등록되었습니다.");
			}

			String temp = null;
			boolean isFound = false;
			int k = 0;
			while (true)
			{
				System.out.print("상영관 : ");
				temp = s.next();

				if (!temp.equals("#"))
				{
					// 예외 처리 및 검색 시작
					for (int i = 0; i < pos; i++)
					{
						if (table[i].getScreen().getName().equals(temp))
						{
							System.out.println("**" + table[i].getScreen().getName() + "은 이미 상영일정이 등록되어 있습니다.");
							isFound = true;
							break;
						}
					}
					if (isFound)
					{
						isFound = false;
						continue;
					}

					for (k = 0; k < sm.getCount(); k++)
					{
						if (sc[k].getName().equals(temp))
						{
							break;
						}
					}
					if (k == sm.getCount())
					{
						System.out.println("**" + temp + "는 미등록된 상영관입니다.");
						continue;
					}
					// 예외 처리 및 검색 끝
				} else
				{
					break;
				}
				s.nextLine();
				System.out.print("상영일정 : ");
				temp = s.nextLine();
				String timeNumbering[] = temp.split(" ");

				table[pos] = new MovieTimetable(m, sc[k], timeNumbering.length);
				table[pos].addTime(temp);
				pos++;
				System.out.println("**상영일정 등록이 완료되었습니다.");
			}
			// 정렬 알고리즘
			for (int i = 0; i < pos - 1; i++)
			{
				for (int j = 0; j < pos - 1 - i; j++)
				{
					if (table[j].getMovie().getTitle().compareTo(table[j + 1].getMovie().getTitle()) > 0)
					{
						//System.out.println("i = " + i + "j = " + j + "pos = " + pos + " ");
						MovieTimetable temp1 = table[j];
						table[j] = table[j + 1];
						table[j + 1] = temp1;
					}
				}
			}
			System.out.println();
			break;
		}

	}

	public void list()
	{
		int i = 0;

		String name = "";
		String isEqualName = null;

		int firstNumbering = 1;
		while (true)
		{
			if (i == pos)
				break;

			isEqualName = table[i].getMovie().getTitle();
			if (!name.equals(isEqualName))
			{
				System.out.print("[" + firstNumbering + "]");
				System.out.println(table[i].getMovie().getTitle());
				name = table[i].getMovie().getTitle();
				firstNumbering++;
			}
			System.out.print(table[i].getScreen().getName() + " : ");
			table[i].showTime();
			System.out.println();
			i++;
		}
		System.out.println();
	}

	public void remove()
	{
		Scanner s = new Scanner(System.in);
		String title = null;
		String name = null;
		boolean isFound = false;

		System.out.print("삭제할 영화제목 ? ");
		title = s.next();

		System.out.print("삭제할 영화관 ? ");
		name = s.next();

		for (int i = 0; i < pos; i++)
		{
			if (table[i].getMovie().getTitle().equals(title) && table[i].getScreen().getName().equals(name))
			{
				for (int j = i; j < pos; j++)
				{
					table[j] = table[j + 1];
				}
				System.out.println(title + "의 " + name + "상영일정이 삭제되었습니다.");
				isFound = true;
				pos--;
				break;
			}
		}
		if (!isFound)
		{
			System.out.println("영화 제목 또는 영화관의 명칭이 일치하지 않아서 삭제하지 못하였습니다. 다시 시도해주세요.");
		}
		System.out.println();
	}

	public void TimetableManagerMain(ScreenManager sm)
	{
		this.sm = sm;

		if (sm.getCount() == 0)
		{
			System.out.println("최소한 하나의 상영관을 등록하십시오.");
			return;
		}

		Scanner s = new Scanner(System.in);
		int input = -999;
		while (input != -1)
		{
			System.out.print("상영일정 메뉴 (1.등록, 2.목록, 3.삭제, 0.메인메뉴 ) >> ");
			input = s.nextInt();
			switch (input)
			{
			case 1:
				add();
				break;
			case 2:
				list();
				break;
			case 3:
				remove();
				break;
			case 0:
				input = -1;
				break;
			default:
				System.out.println("입력오류입니다.");
				break;
			}
		}
	}

	public int getPos()
	{
		return pos;
	}

	public MovieTimetable[] getMT()
	{
		return table;
	}
	/*
	 * public MovieTimetable get(Movie movie, Screen screen) { for(int i = 0; i <
	 * pos; i++) { if(table[i].getMovie().equals(movie)) {
	 * 
	 * }
	 * 
	 * } return }
	 */
}