/*ID:20132071 DATE:2019-05-11*/
package myPackage;

import java.util.Scanner;

public class TicketManager
{
	private static int serialCode = 10000;
	private TimetableManager tm;
	private Ticket[] tickets;
	int pos;

	public TicketManager(int n)
	{
		tickets = new Ticket[n];
		pos = 0;
	}

	public void add()
	{
		Movie p_movie = null;
		Screen p_screen = null;
		String playHour = null;
		String ticketNo = null;
		Seat[] p_seat = null;

		String title, screen, time;
		int selectRow;
		String selectSeat = null;
		String seats[] = null;
		int selectSeats[] = null;
		Scanner s = new Scanner(System.in);
		boolean switch1, switch2, switch3;
		switch1 = switch2 = switch3 = false;

		// tm.list(); 디버그용 출력

		System.out.print("영화제목 : ");
		title = s.next();
		System.out.print("상영관 및 상영시간 : ");
		screen = s.next();
		time = s.next();

		for (int i = 0; i < tm.getPos(); i++)
		{
			if (tm.getMT()[i].getMovie().getTitle().equals(title) && tm.getMT()[i].getScreen().getName().equals(screen))
			{
				switch1 = true;
				for (int j = 0; j < tm.getMT()[i].getTimes().length; j++)
				{
					if (tm.getMT()[i].getTimes()[j].equals(time))// 이 부분에서 공백을 포함하는지에 따라 오류를 확인해야함
					{
						p_movie = tm.getMT()[i].getMovie();
						p_screen = tm.getMT()[i].getScreen();
						playHour = tm.getMT()[i].getTimes()[j];
						switch2 = true;
						break;
					}
				}
				if (!switch2)
				{
					System.out.println("영화제목, 상영관 및 상영시간이 일치하는 것이 없습니다. 다시 한번 확인해주세요.");
					System.out.println("debug : " + p_movie + " " + title + " " + p_screen + " " + screen + " "
							+ playHour + " " + time);
					break;
				}
			}
		}
		if (!switch1)
		{
			System.out.println("영화제목, 상영관이 일치하는 것이 없습니다. 다시 한번 확인해주세요.");
			System.out.println("debug : " + p_movie + " " + title + " " + p_screen + " " + screen);
			return;
		}

		System.out.println(
				"**" + p_screen.getName() + "은 " + p_screen.getRowNum() + "열 " + p_screen.getSeatNum() + "좌석 상영관입니다.");
		while (true)
		{
			System.out.print("열번호 : ");
			selectRow = s.nextInt();
			System.out.print("좌석 : ");
			s.nextLine();
			selectSeat = s.nextLine();
			seats = selectSeat.split(" ");// 실패하면 null초기화
			selectSeats = new int[seats.length];// 실패하면 null초기화

			for (int i = 0; i < seats.length; i++)
			{
				selectSeats[i] = Integer.parseInt(seats[i]);// 실패하면 null초기화
			}

			if (pos > 0)
			{
				for (int i = 0; i < pos; i++)
				{
					for (int j = 0; j < tickets[i].getSeats().length; j++)
					{
						for (int k = 0; k < selectSeats.length; k++)
						{
							if (tickets[i].getSeats()[j].getRowNo() == selectRow
									&& tickets[i].getSeats()[j].getSeatNo() == selectSeats[k])
							{
								switch3 = true;
							}
						}
					}
				}
				if (switch3 == true)
				{
					System.out.println("이미 예약되어있는 좌석입니다. 다시 선택해주세요.");
					seats = null;
					selectSeat = null;
					selectSeats = null;
					System.out.println();
					continue;
				} else
				{
					break;
				}
			} else
			{
				break;
			}
		}

		System.out.print("예약코드 : ");
		ticketNo = s.next();
		ticketNo += serialCode;
		serialCode++;

		p_seat = new Seat[selectSeats.length];

		for (int i = 0; i < selectSeats.length; i++)
		{
			p_seat[i] = new Seat(selectRow, selectSeats[i]);
		}
		tickets[pos] = new Ticket(ticketNo, p_movie, p_screen, playHour, p_seat);
		pos++;
		System.out.print("**");
		for (int i = 0; i < p_seat.length - 1; i++)
		{
			System.out.print(p_seat[i].getRowNo() + "열 " + p_seat[i].getSeatNo() + "번, ");
		}
		System.out
				.println(p_seat[p_seat.length - 1].getRowNo() + "열 " + p_seat[p_seat.length - 1].getSeatNo() + "번 좌석");
		System.out.println();
	}

	public void list()
	{
		Scanner s = new Scanner(System.in);

		String ticketNo;
		boolean isFound = false;
		System.out.print("예약번호 : ");
		ticketNo = s.next();

		for (int i = 0; i < pos; i++)
		{
			if (tickets[i].getTicketNo().equals(ticketNo))
			{
				System.out.println(tickets[i].getMovie().getTitle());
				System.out.print(tickets[i].getScreen().getName());
				System.out.println(tickets[i].getPlayHour());
				System.out.print("**");
				for (int j = 0; j < tickets[i].getSeats().length - 1; j++)
				{
					System.out.print(tickets[i].getSeats()[j].getRowNo() + "열 "
							+ tickets[i].getSeats()[j].getSeatNo() + "번, ");
				}
				System.out.println(tickets[i].getSeats()[tickets[i].getSeats().length - 1].getRowNo() + "열 "
						+ tickets[i].getSeats()[tickets[i].getSeats().length - 1].getSeatNo() + "번 좌석");
				isFound = true;
				break;
			}
		}
		if (!isFound)
		{
			System.out.println("해당 티켓은 존재하지 않거나 잘못된 이름의 티켓입니다.");
		}
		System.out.println();
	}

	/*
	 * public Ticket get(String ticketNo) { for (int i = 0; i < pos; i++) { if
	 * (tickets[i].getTicketNo().equals(ticketNo)) { return tickets[i]; } }
	 * System.out.println("Error : Can't get Ticket Check TicketManager"); return
	 * null; }
	 */

	public void remove()
	{
		Scanner s = new Scanner(System.in);
		String ticketNo;
		boolean isFound = false;

		System.out.print("예약번호 : ");
		ticketNo = s.next();

		for (int i = 0; i < pos; i++)
		{
			if (tickets[i].getTicketNo().equals(ticketNo))
			{
				for (int j = i; j < pos; j++)
				{
					tickets[j] = tickets[j + 1];
				}
				System.out.println("예약이 취소되었습니다.");
				isFound = true;
				pos--;
				break;
			}
		}
		if (!isFound)
		{
			System.out.println("해당 예약번호는 존재하지 않습니다.");
		}
		System.out.println();
	}

	public void ticketManagerMain(TimetableManager tm)
	{
		this.tm = tm;

		if (tm.getPos() == 0)
		{
			System.out.println("최소한 하나의 상영관과 상영영화 일정을 등록하십시오.");
			return;
		}

		Scanner s = new Scanner(System.in);

		int input = -999;

		while (input != -1)
		{
			System.out.print("영화티켓 예매 메뉴(1.예매, 2.확인, 3.취소, 0,메인으로) >> ");
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
				System.out.println("프로그램을 종료합니다.");
				input = -1;
				break;
			default:
				System.out.println("입력오류입니다.");
				break;
			}
		}
	}
}