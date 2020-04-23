/*ID:20132071 DATE:2019-05-11*/
package myPackage;

public class MovieTimetable
{
	private Movie movie;
	private Screen screen;
	private String[] times;

	public MovieTimetable(Movie movie, Screen screen, int count)
	{
		this.movie = movie;
		this.screen = screen;
		times = new String[count];
	}

	public void addTime(String t)
	{
		times = t.split(" ");
	}

	public void showTime()
	{
		for (int i = 0; i < times.length; i++)
		{
			System.out.print(times[i] + " ");
		}
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

	public String[] getTimes()
	{
		return times;
	}

	public void setTimes(String[] times)
	{
		this.times = times;
	}
}