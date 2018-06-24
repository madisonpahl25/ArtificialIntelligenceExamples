
JC = javac
JCR = java

.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
	Main.java \
	RobotGrid.java \
	Robot.java \
	Comparison.java\
	RobotState.java \



default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class *~

.PHONY: default clean classes exec-tests