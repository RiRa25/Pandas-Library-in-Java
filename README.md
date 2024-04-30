# RiRa25-TechTribe-24-PandasLibraryinJava

# -"Developing a Pandas Library in Java for Data Manipulation and Analysis"

There are two Java Files:

Path of Implemented Java file on GitHub : DataFrame -> src -> Pandas -> DataFrame.java

Path of Main Java file on GitHub : DataFrame -> src -> PandasImplementation -> implementation.java

15 days Report Part 1: https://drive.google.com/file/d/1R18rocuTFUHpl4HoBmyhC43PM5xzve0i/view?usp=sharing

15 days Report Part 2: https://drive.google.com/file/d/1r1XOYNLR-jNCFrGRiiXkLr8ie9ou0C76/view?usp=sharing

Project Report: https://drive.google.com/file/d/1gQ5SXzsoPDYgvIvs_0UGrSu2wuYWXHU0/view?usp=sharing

Video Link: https://drive.google.com/file/d/14mhADGuM3zFoIYLIVzyW4ji9yQbVPAPh/view?usp=sharing

Drive Link: https://drive.google.com/drive/folders/1Ix1RRPqXSxsXQb-TE0vpQPoTAEkEM_DV?usp=sharing

# Description:

This project aims to create a DataFrame library in Java, offering functionalities similar to Python's Pandas library. The Java DataFrame will allow users to perform key data manipulation tasks such as sorting, filtering, adding/removing columns and rows, reading and generating CSV files, and more.

Python's Pandas DataFrame is a widely used data structure that allows users to work with two-dimensional data, similar to SQL tables or Excel spreadsheets. The DataFrame's flexibility and ease of use have made it a preferred choice for data science and analysis.

By developing a DataFrame library for Java, this project will provide Java developers with a powerful tool for data manipulation and analysis. This will enable Java developers to conduct data science tasks without needing to switch to other programming languages.

#-Data Structures used:

1.	List of Maps (`List<Map<String, Object>>`): The primary data structure for storing rows of the DataFrame. Each map represents a single row, with keys as column names and values as data.
  
2.	Set of Strings (`Set<String>`): A set is used to maintain a unique collection of column names. This set helps ensure that column names are unique and provides a way to track which columns are part of the Pandas.
   
3.	ArrayList (`ArrayList<T>`): Used in various operations, such as maintaining a list of column names in a specific order or returning a subset of rows. The `ArrayList` is preferred for its dynamic size and random-access performance.
   
4.	HashSet (`HashSet<T>`): Used to store column names for fast membership checks. The `HashSet` provides quick lookup for column names, helping ensure uniqueness.
   
5.	HashMap (`HashMap<K, V>`): The `HashMap` is used to represent individual rows within the DataFrame. It allows efficient key-based retrieval of column values and supports dynamic insertion and removal of key-value pairs.

