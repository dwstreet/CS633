For access to the prebuilt executable please access the master branch and download the file "Reservations"

Setting up a the development environment instructions are below.

# CS633
Reservation System

I'm using Eclipse and have provided some links for setting up your own workspace.

Setup instructions (Eclipse):

1) Follow instructions at this link https://download.java.net/general/javafx/eclipse/tutorial.html
2) Import this git project into your workspace
3) If your project's eclipse natures are incorrect then copy the xml underneath into your .project file (auto created after you import the project to your workspace)
4) Ensure that you are using Jdk8 or greater (you can use this link for more information -- https://www.gamefromscratch.com/post/2011/11/15/Telling-Eclipse-to-use-the-JDK-instead-of-JRE.aspx)
5) Ensure that your project is actually using the JavaFx library in the BuildPath

-----------------------------------
MY .project CONTENTS
-----------------------------------

<?xml version="1.0" encoding="UTF-8"?>
<projectDescription>
	<name>asd</name>
	<comment></comment>
	<projects>
	</projects>
	<buildSpec>
		<buildCommand>
			<name>org.eclipse.jdt.core.javabuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
		<buildCommand>
			<name>org.eclipse.xtext.ui.shared.xtextBuilder</name>
			<arguments>
			</arguments>
		</buildCommand>
	</buildSpec>
	<natures>
		<nature>org.eclipse.xtext.ui.shared.xtextNature</nature>
		<nature>org.eclipse.jdt.core.javanature</nature>
	</natures>
</projectDescription>
