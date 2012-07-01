(ns vasttrafik-proxi.core)
(use 'lamina.core 'aleph.tcp 'gloss.core)

(def counter (atom 0))

(defn handler [ch client-info]
  (receive ch
           (fn [msg]
             (enqueue ch (str "   You called\n   " (swap! counter inc) " times!"))
             (close ch))))

(defn -main []
  (start-tcp-server handler {:port 1234, :frame (string :utf-8 :delimiters ["\r\n"])}))
