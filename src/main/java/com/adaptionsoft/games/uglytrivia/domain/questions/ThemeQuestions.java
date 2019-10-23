package com.adaptionsoft.games.uglytrivia.domain.questions;

import java.util.List;

public interface ThemeQuestions {

  List<String> list();

  void generate(int numberOfQuestions);
}
