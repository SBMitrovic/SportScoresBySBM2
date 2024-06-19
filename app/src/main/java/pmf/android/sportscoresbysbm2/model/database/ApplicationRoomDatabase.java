package pmf.android.sportscoresbysbm2.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import pmf.android.sportscoresbysbm2.model.Competition;
import pmf.android.sportscoresbysbm2.model.Country;
import pmf.android.sportscoresbysbm2.model.League;


/*@Database(entities = {/*Country.class, League.class,Competition.class}, version = 1, exportSchema = false)*/
public abstract class ApplicationRoomDatabase extends RoomDatabase {

    /*
    public abstract CountryDao countryDao();
    public abstract LeagueDao leagueDao();
    private static ApplicationRoomDatabase INSTANCE;

    public static ApplicationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationRoomDatabase.class, "application_database")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        LeagueDao leagueDao = INSTANCE.leagueDao();
        CountryDao countryDao = INSTANCE.countryDao();

        return INSTANCE;
    }

  //  public abstract CompetitionDao competitionDao();
    */
}
