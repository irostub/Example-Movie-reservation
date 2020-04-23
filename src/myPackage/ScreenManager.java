/*ID:20132071 DATE:2019-05-11*/
package myPackage;

import java.util.Scanner;

public class ScreenManager
{
	private Screen[] screens;
	private int maxCount;
	private int count;

	public ScreenManager(int maxCount)
	{
		this.maxCount = maxCount;
		this.screens = new Screen[maxCount];
		count = 0;
	}

	public void add()
	{
		String name = null;
		int rowNum = 0, seatNum = 0;
		Scanner s = new Scanner(System.in);
		if (count == maxCount)
		{
			System.out.println("상영 가능한 스크린 수가 모두 차있습니다.");
		} else
		{
			System.out.print("상영관 명칭 : ");
			name = s.next();
			System.out.print("좌석 열수 : ");
			rowNum = s.nextInt();
			System.out.print("한열의 좌석수 : ");
			seatNum = s.nextInt();
			System.out.println("# " + name + "이 생성되었습니다.");
			System.out.println();
			screens[count] = new Screen(name, rowNum, seatNum);
			count++;
		}
	}

	public void list()
	{
		for (int i = 0; i < count; i++)
		{
			System.out.println(
					screens[i].getName() + "[" + screens[i].getRowNum() + "열 / " + screens[i].getSeatNum() + "좌석]");
		}
		System.out.println();
	}

//	public void get() 
//	{
//		
//	}

	public void remove()
	{
		Scanner s = new Scanner(System.in);
		System.out.print("삭제할 상영관 명칭? ");
		String input = s.next();
		boolean isfound = false;
		for (int i = 0; i < count; i++)
		{
			if (input.equals(screens[i].getName()))
			{
				System.out.println("# " + input + "이 삭제되었습니다.");

				for (int j = i; j < count; j++)
				{
					screens[j] = screens[j + 1];
				}
				isfound = true;
				count--;
				break;
			}
		}
		if (!isfound)
		{
			System.out.println("삭제할 상영관 명칭이 잘못 된 것 같습니다. 다시 시도해주세요.");
		}
		System.out.println();
	}

	public void screenManagerMain()
	{
		Scanner s = new Scanner(System.in);
		int input = -999;
		while (input != -1)
		{
			System.out.print("상영관 메뉴 (1.등록, 2.목록, 3.삭제, 0.메인메뉴) >> ");
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

	public Screen[] getScreens()
	{
		if (count == 0)
		{
			return null;
		}
		return screens;
	}

	public void setScreens(Screen[] screens)
	{
		this.screens = screens;
	}

	public int getMaxCount()
	{
		return maxCount;
	}

	public void setMaxCount(int maxCount)
	{
		this.maxCount = maxCount;
	}

	public int getCount()
	{
		return count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}
}