<p>In this assignment, you are going to realiza a simple chat program by means of sockets.
Please recall the correspondings slides from the lecture.
subsequently, implement the public static method <code>main</code> of class <code>Chat</code>.</p>
<p>The communication is meant to proceed as follows.</p>
<ol>
<li>The program asks the user for some input (already provided in the template).
This query is repeated until a connection has been established or the user types <code>exit</code>.<ol>
<li>If the user inputs something without <code>:</code>, the input is interpreted as a port, and the program
tries to create a <code>ServerSocket</code> for that port and wait for connections.
The server waits for exactly one connection.</li>
<li>If the user inputs something with a <code>:</code>, the input is interpreted as <code>&lt;host&gt;:&lt;port&gt;</code> and
the program tries to connect with the given host at the specified port.</li></ol></li>
<li>The two participants exchange messages. <strong>The server starts to send.</strong> Alternatingly, the
program waits for input from the user and the socket.</li>
<li>One of the participants inputs <code>exit</code>. Then both programs should terminate.</li>
</ol>
<p>Handle all possibly occurring exceptions appropriately. Output precise messages and decide whether you
may want to continue program execution despite the exception or not.
Make sure that when terminating the program, all sockets as well as all Writers and Readers have
been closed. Instead of calling <code>close()</code> at appropriate places, you also may use
<code>finally</code> blocks <a rel="noopener noreferrer" href="https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html">try-with-resource-statements</a>.</p>
<p>Test your program by chatting with yourself or one of your fellow students!
Take care that you only use port numbers exceeding 1024.
Be aware that you may have to enable your program by your firewall.</p>
<ul>
<li>In order to chat with yourself, you should first start the program as Server and then start another
instance of the program and connect that with the server. Your local host is called <code>localhost</code>.
Please note that Eclipse has only a single console window. You may, though, by means of
Beachten Sie, dass Eclipse nur ein Konsolenfenster
<a rel="noopener noreferrer" href="https://syncandshare.lrz.de/getlink/fiEpAadHAz8HBe7fgXwPbduc/eclipseConsole.png?inline">this button</a>
switch between different programs; Eclipse may also switch autonomously, when the output has changed.</li>
<li>In order to chat with your fellow students, one of you should start the server, determines her
IP address (e.g., by the command <code>ipconfig</code> in a terminal shell).
The other then can connect to that server via the ip as host.
For that it is mandatory that both computers are connected to the same subnet (e.g., within the same WLAN).</li>
</ul></div>
</div><!---->
<!---->
</jhi-programming-exercise-instructions><!---->
