Feistel Cipher is implemented here.
First I take the text and if text length odd add a character to there.
append ";;;;" to identify the new lines.

Then as the feistel function i used xor method.
I took (key xor Right)=result and then (result xor left) and swap
used 3 iterations.
can extend upto any number of iterations using more keys.
if you need to iterate more times just add more keys to the array.
Key length is taken same as the Right or left

you can give any number of keys to make the encrypted message more complex.
there is an array for keys in the both classes.
i send a bit stream in the encrypted message text. so the encrpted message bits do not alter in transmission.
this bit stream just represent the encrpted message and it does not reduce the security of the message.

decrpted/encrpted message is saved in a file
