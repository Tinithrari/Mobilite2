package model;

/**
 * 
 */
public class DefiScore {

    /**
     * Default constructor
     */
    public DefiScore() {
    }

    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     *
     */
    private long score;

    /**
     * @param id
     * @param name
     * @param score
     */
    public void DefiScore(Long id, String name, long score) {
        this.id = new Long(id);
        this.name = name;
        this.score = score;
    }

    /**
     * @param name
     * @param score
     */
    public void DefiScore(String name, long score) {
        this.id = new Long(0);
        this.name = name;
        this.score = score;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public long getScore() {
        return score;
    }

    /**
     *
     * @param score
     */
    public void setScore(long score) {
        this.score = score;
    }
}