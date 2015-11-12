package com.chinasoft.model.performance;

public class SelfJudgeScore {
	private int id;
	private String name;
	private int weight;
	private Score score;
	private int score1;
	private int score2;
	private int totalScore;
	private String  description;
	private double selfAvgscore;
	public double getSelfAvgscore() {
		return selfAvgscore;
	}
	public void setSelfAvgscore(double selfAvgscore) {
		this.selfAvgscore = selfAvgscore;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Score getScore() {
		return score;
	}
	public void setScore(Score score) {
		this.score = score;
	}
	public int getScore1() {
		return score1;
	}
	public void setScore1(int score1) {
		this.score1 = score1;
	}
	public int getScore2() {
		return score2;
	}
	public void setScore2(int score2) {
		this.score2 = score2;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
