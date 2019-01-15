; intersection 2 sets


(defun finde(L e)
  (cond
  ((null L) nil)
  ((equal (car L) e)  t)
  (T (finde (cdr L) e))
  )
)


(defun concat(L1 L2)
  (cond
  ((null L1) L2)
  ((finde L2 (car L1)) (concat (cdr L1) L2))
  (T (cons (car L1) (concat (cdr L1) L2)))
  )
)


(defun buildreverse(L)
  (cond
  ((atom L) (list L))
  ((listp L) (apply 'append(reverse (mapcar 'buildreverse L))))
  )

)

; Write a function to replace each sublist of a list with its last element.



(defun isliniar(L)
  (cond
  ((null L) t)
  ((listp (car L)) nil)
  (T (isliniar (cdr L)))
  )
)

(defun getlast(L)
  (cond
  ((atom L) L)
  ((null (cdr L)) (car L))
  (T (getlast (cdr L)))
  )
)

(defun replacelast (L)
  (cond
  ((and (print L) (isliniar L) L))
  (T (replacelast (mapcar 'getlast L)))
  )
)



; Convert a tree of type (2) to type (1)

(defun getlength(L)
  (cond
  ((null L) 0)
  (T (+ 1 (getlength (cdr L ))))
  )
)


(defun convert(L)
  (cond
  ((equal (getlength L) 1) (append L '(0)))
  ((equal (getlength L) 2) (append (list (car L)) '(1) (convert (nth 1 L))))
  ((equal (getlength L) 3) (append (list (car L)) '(2) (convert (nth 1 L)) (convert (nth 2 L))))
  )
)



; the depth of lvl k


(defun lvlk(L lvl)
  (cond
  ((and (atom L) (= (mod lvl 2) 0))   1 )
  ((atom L) 0)
  (T  (apply '+(mapcar (lambda ( e ) (lvlk e (+ 1 lvl)) )  L)) )
  )

)


(defun removen(L n)
  (cond
  ((and (atom L) (equal n L)) (list))
  ((atom L) (list L))
  (T (list (apply 'append(mapcar (lambda (e) (removen e n)) L))))
  )
)
