(defun finde(L e)
  (cond
  ((null L) nil)
  ((and (atom (car L)) (equal e (car L)))  T)
  ((atom (car L)) (finde (cdr L) e))
  ((listp (car L)) (or (finde(car L) e) (finde(cdr L) e )))
  )
)

(defun eliminate(e L ok)
  (cond
  ((null L) (list))
  ((and (atom (car L)) (equal (car L) e) (equal ok nil)) (eliminate e (cdr L) t))
  ((atom (car L)) (cons (car L)  (eliminate e (cdr L) ok)))
  ((listp (car L)) (cons (eliminate e (car L) ok) (eliminate e (cdr L) (finde (car L) e))))
  )
)

(defun elimin (e L)
  (cond
    (T (eliminate e L nil))
  )
)
