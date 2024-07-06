package pmf.android.sportscoresbysbm2.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {TeamEntity.class}, version = 1, exportSchema = false)
public abstract class ApplicationRoomDatabase extends RoomDatabase {


    private static ApplicationRoomDatabase INSTANCE;

    public abstract TeamEntityDao teamEntityDao();
    public static ApplicationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ApplicationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ApplicationRoomDatabase.class, "application_database")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }

        return INSTANCE;
    }


}
