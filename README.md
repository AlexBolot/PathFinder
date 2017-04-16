<h1>PathFinder</h1>
<h2>Summary :</h2>
<ol>
<li><a href="https://github.com/AlexBolot/PathFinder#1---about-the-project ">About the project</a><br />
<ol>
<li><a href="https://github.com/AlexBolot/PathFinder#11---the-goal ">The goal</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder#12---how-to-use ">How to use</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder#13---selecting-types ">Selecting types</a></li>
</ol>
</li>
<li><a href="https://github.com/AlexBolot/PathFinder#2---package-tree-organisation ">Package tree organisation</a>
<ol>
<li><a href="https://github.com/AlexBolot/PathFinder#21---controller ">Controller</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder#22---model ">Model</a></li>
<ol>
<li><a href="https://github.com/AlexBolot/PathFinder#221---app ">Case</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder#222---grid ">Grid</a></li>
</ol>
<li><a href="https://github.com/AlexBolot/PathFinder#23---service ">Service</a></li>
</ol>
</li>
<li><a href="https://github.com/AlexBolot/PathFinder/#3---coming-soon ">Coming soon</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder/#4---libraries ">Libraries</a></li>
<li><a href="https://github.com/AlexBolot/PathFinder/#5---author ">Author</a></li>
</ol>
<h2>1 - About the project</h2>
<p>This project is a personal project, I started it after attending to an algorithmic class in my University.</p>
<h3>1.1 - The goal</h3>
<p>It allows&nbsp;you to find the shortest path from A to B, on a grid. It takes in parameter the number of&nbsp;nodes and the "cost" of each node. (See <a href="https://github.com/AlexBolot/PathFinder#13---selecting-types">Selecting Types</a>)</p>
<h3>1.2 - How to use</h3>
<p>When the Application starts, 2 GUI Views show up. The first one is the grid, the second one the legend of the nodes. You can now set up the grid :</p>
<ol>
<li>Mark the start point by typing&nbsp;'D' in the node of your choice</li>
<li>Mark the arrival point by typing 'A' in the node of your choice</li>
<li>From now on you can just move to step 4 or keep adding complexity on the grid :
<ol>
<li>You can generate random 'walls' by pressing the so-named button</li>
<li>You can add set any node with a speceific type by typing the corresponding letter (LOGO) in it</li>
</ol>
</li>
<li>Press 'Solve' to process and show the shortest path</li>
<li>Pressing 'Reset' will remove the path but leave any specified node intact, so you can change them manually.</li>
</ol>
<h3>1.3 - Selecting types</h3>
<p>Selecting the types of challenges you want to play with is made with the first GUI.<br />Two ListViews are displayed :</p>
<ol>
<li>The list of all known challenge types</li>
<li>The list of the challenge types you want to use in this game</li>
</ol>
<p>You can Drag and Drop the challenge types to put them in the appropriate list.<br />When you are satisfied with you choice, just press the Start button to play !</p>
<h2>2 - Package tree organisation</h2>
<h3>2.1 - Controller</h3>
<p>In this package, as the name says it, you will find the java classes working as controller of the JavaFX views (.fxml). There is not much more to say about this package.</p>
<h3>2.2 - Model</h3>
<h4>2.2.1 - Case</h4>
<p>This class starts the JavaFX Application and calls the first GUI (ChallengeTypesView). It also creates the Players (for now, it will change in the next update).</p>
<h4>2.2.2 - Grid</h4>
<p>This class creates all the challenges and adds them to the ChallengeManager if they match the <a href="https://github.com/AlexBolot/PathFinder#13---selecting-types">list of selected challenge types</a>.<br />Note : this class will be removed in the next update.</p>
<h3>2.3 - Service</h3>
<h4>2.3.1 - App</h4>
<p>This class is composed only of public static fields.<br />The goal is to centralise most variables, to be able to change them only from here. It reduces typo mistakes and allows quick modifiaction (view size, view path, etc).</p>
<h2>3 - Coming soon</h2>
<p>Please check the TODO.md file in this repository to find out what updates are planned.</p>
<h2>4 - Libraries</h2>
<p>Only 3 libraries are used for this Maven QuickStart project</p>
<ul>
<li>Java8 : JDK 1.8.111</li>
<li>Maven : JUnit 4.12</li>
<li>Maven : hamcrest-core:1.3</li>
</ul>
<p>Note : those libraries are already included in : org.jetbrains.idea.maven.model.MavenArchetype@88f75e0f</p>
<h2>5 - Author</h2>
<p>Alexandre BOLOT<br />My <a href="https://github.com/AlexBolot">GitHub</a><br />My <a href="https://www.linkedin.com/in/alexandrebolot">LinkedIn</a></p>
