package myPackage;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		ScreenManager sm = new ScreenManager(10);
		TimetableManager ttm = new TimetableManager(10);
		TicketManager tm = new TicketManager(10);

		while (true)
		{
			int x = 0;
			printMenu();

			System.out.print("선택 >> ");
			x = sc.nextInt();

			switch (x) {
			case 0:
				return;
			case 1:
				sm.screenManagerMain();
				break;
			case 2:
				ttm.TimetableManagerMain(sm);
				break;
			case 3:
				tm.ticketManagerMain(ttm);
				break;
			}
		}
	}

	private static void printMenu()
	{
		System.out.println("+---------------------+");
		System.out.println("    영화 예매 프로그램");
		System.out.println("+---------------------+");
		System.out.println("  1.상영관 관리");
		System.out.println("  2.영화 상영일정 관리");
		System.out.println("  3.영화 예매 관리");
		System.out.println("  0.프로그램 종료");
	}

}
