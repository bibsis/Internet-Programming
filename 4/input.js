regExps = {
"exercise_1": /[A-Z][a-z]+/,
"exercise_2": /088[1|7]...[5|7|3]../,
"exercise_3": /\D+|[^1^0]/,
"exercise_4": /(^v.*e$|^i.*)/,
"exercise_5": /(^1|^9).+(9$|0$)/,
"exercise_6": /class=('|").*[^ \W]('|")/
};
cssSelectors = {
"exercise_1": "item#someID + item>java",
"exercise_2": "different#diffId *, different#diffId2 *",
"exercise_3": "java.class1 tag",
"exercise_4": "item#someID + item",
"exercise_5": "css>item:first-child tag java:nth-child(2)",
"exercise_6": "item.class2>item.class1 ,item.class1>item.class2",
"exercise_7": "different#diffId2 java.diffClass:last-child",
"exercise_8": "item#someId"
};
