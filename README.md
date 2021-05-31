<h1>Welcome to the ProblemBook application!</h1>

<p>ProblemBook is a network destinated for use by both students and teachers alike. It features a simple interface for adding problems (by teachers) and submitting solutions (by students).</p>

<h2>How do I use it?</h2>
<p>Just open the app and a login interface will pop up. In it, you will have 3 fields to fill:</p>
<ul>
<li>Name : this is the name you used to register</li>
<li>Password : this is where you input your password (the field auto-converts all characters to '*' on display, so that your password cannot be seen)</li>
<li>As a teacher : if checked, your credentials will be interpreted as a <strong>teacher's</strong>(and checked in the appropiate database table), otherwise you login as a student (meaning that the credentials will be treated as such)</li>
</ul>

<h2>How do I register?</h2>
<p>There are two types of users: teachers and students.</p>
<h3>Registering as a teacher</h3>
<p>Just hit the <strong>Register as Teacher</strong> button and a GUI will pop up. Fill in the blank fields and then hit register. If the credentials are ok, a <strong>Registration successful</strong> message will appear at the bottom. Otherwise, the message will contain information on what went wrong (password too short, incorrect name, incorrect email, etc.).</p>

<h3>Registering as a student</h3>
<p>Follow the same instructions as you would do if you wanted to register as a teacher. Just hit the <strong>Register as Student</strong> button and you are good to go!</p>

<h2>The teacher GUI</h2>
<p>As a teacher, you will be able to add problems to a cloud deployed database. A <strong>problem</strong> consists of the following:</p>
<ul>
<li>content : this is the main text of the problem. It can have up to 2000 charaters.</li>
<li>first hint : for a problem to be easier, you need to include two hints. This is the first one, limited to 100 characters.</li>
<li>second hint : limited again to 100 characters, this should shine even more light on the problem.</li>
</ul>
<p>Besides those, a problem also has an ID, the author's ID and his email.</p>

<h2>The student GUI</h2>
<p>As a student, you will be able to see problems, search for them, go from one problem to the next one or to the previous one, and analyse it in detail by viewing the problem's content and hints. Once you are confident that you have resolved a problem, you can use the <strong>Send email</strong> button to send a mail to the problem's author with a pregenerated subject and message body, but you will be prompted to select a file representing the solution to the problem.</p>

<h2>Other important notes</h2>
<p>Both types of users have an avatar associated with their profile. This avatar is a simple image which makes the GUI look more friendly!</p>
<p>Both types of users have a <strong>Log out</strong> button for exiting the application. Also, they have a <strong>Console Log</strong> where important information will pop up (for example, if you search for a problem with an invalid ID, a log will appear warning you that you made a mistake in writing the ID).</p>

<h2>Version</h2>
<p>Current version is 1.0 with future releases coming!</p>