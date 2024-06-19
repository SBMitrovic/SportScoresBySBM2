package pmf.android.sportscoresbysbm2.model.database;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeamEntityDao {

    @Query("SELECT * FROM favourite_teams")
    List<TeamEntity> getAll();

    @Query("SELECT * FROM favourite_teams WHERE id = :teamId")
    TeamEntity getTeamById(Long teamId);

    @Delete
    void deleteTeam(TeamEntity teamEntity);

    @Insert
    void insert(TeamEntity teamEntity);


}
