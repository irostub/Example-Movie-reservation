/*ID:20132071 DATE:2019-05-11*/
package myPackage;

public class Ticket
{
	private String ticketNo;
	private Movie movie;
	private Screen screen;
	private String playHour;
	private Seat[] seats;

	public Ticket(String ticketNo, Movie movie, Screen screen, String playHour, Seat seats[])
	{
		this.ticketNo = ticketNo;
		this.movie = movie;
		this.screen = screen;
		this.playHour = playHour;
		this.seats = seats;
	}

	public String getTicketNo()
	{
		return ticketNo;
	}

	public void setTicketNo(String ticketNo)
	{
		this.ticketNo = ticketNo;
	}

	public Movie getMovie()
	{
		return movie;
	}

	public void setMovie(Movie movie)
	{
		this.movie = movie;
	}

	public Screen getScreen()
	{
		return screen;
	}

	public void setScreen(Screen screen)
	{
		this.screen = screen;
	}

	public String getPlayHour()
	{
		return playHour;
	}

	public void setPlayHour(String playHour)
	{
		this.playHour = playHour;
	}

	public Seat[] getSeats()
	{
		return seats;
	}

	public void setSeats(Seat[] seats)
	{
		this.seats = seats;
	}
}