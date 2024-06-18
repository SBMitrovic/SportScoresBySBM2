package pmf.android.sportscoresbysbm2.model.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

import pmf.android.sportscoresbysbm2.model.Competition;


@Dao
public interface CompetitionDao {

    /*
    //Ovde ce se trebati dodati ID drzave da bi se dobila takmicenja bas za tu drzavu
    @Query("SELECT * FROM competition")
    List<Competition> getAll();

    @Insert
    void insert(Competition competition);

    @Delete
    void delete(Competition competition);  */
}
