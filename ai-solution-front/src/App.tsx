import React, { useState } from "react";
import { ChatList } from "./components/ChatList";
import { ChatWindow } from "./components/ChatWindow";
import axios from "./lib/axios";

export interface Chat {
  id: string;
  title: string;
  preview: string;
  timestamp: string;
}

export interface Message {
  id: string;
  content: string;
  isAi: boolean;
  timestamp: string;
}

export type Theme = "dark" | "light";
export type AccentColor = "blue" | "purple" | "green" | "pink" | "orange";

export default function App() {
  const [theme, setTheme] = useState<Theme>("dark");
  const [accentColor, setAccentColor] = useState<AccentColor>("blue");

  const [chats] = useState<Chat[]>([
    {
      id: "1",
      title: "React 프로젝트 도움",
      preview: "React Hook에 대해 설명해주세요",
      timestamp: "오전 10:30",
    },
    {
      id: "2",
      title: "코딩 테스트 준비",
      preview: "알고리즘 문제 풀이 방법을...",
      timestamp: "어제",
    },
    {
      id: "3",
      title: "디자인 시스템 구축",
      preview: "Tailwind CSS 활용법에 대해",
      timestamp: "2일 전",
    },
    {
      id: "4",
      title: "데이터베이스 설계",
      preview: "SQL과 NoSQL의 차이점은?",
      timestamp: "3일 전",
    },
  ]);

  const [selectedChatId, setSelectedChatId] = useState<string>("1");
  const [isSidebarOpen, setIsSidebarOpen] = useState<boolean>(true);
  const [messages, setMessages] = useState<Message[]>([
    {
      id: "1",
      content: "React Hook에 대해 설명해주세요",
      isAi: false,
      timestamp: "오전 10:30",
    },
    {
      id: "2",
      content:
        "React Hook은 함수형 컴포넌트에서 상태(state)와 생명주기(lifecycle) 기능을 사용할 수 있게 해주는 기능입니다.\n\n주요 Hook:\n• useState: 상태 관리\n• useEffect: 사이드 이펙트 처리\n• useContext: Context API 활용\n• useRef: DOM 접근 및 값 저장\n\n사용 예시가 필요하시면 말씀해주세요!",
      isAi: true,
      timestamp: "오전 10:31",
    },
    {
      id: "3",
      content: "useState 사용 예시를 보여줘",
      isAi: false,
      timestamp: "오전 10:32",
    },
    {
      id: "4",
      content:
        "네! useState의 기본 사용법을 보여드리겠습니다.\n\n```jsx\nimport React, { useState } from 'react';\n\nfunction Counter() {\n  const [count, setCount] = useState(0);\n\n  return (\n    <div>\n      <p>현재 카운트: {count}</p>\n      <button onClick={() => setCount(count + 1)}>\n        증가\n      </button>\n    </div>\n  );\n}\n```\n\n이렇게 useState를 사용하면 함수형 컴포넌트에서도 상태를 관리할 수 있습니다!",
      isAi: true,
      timestamp: "오전 10:32",
    },
  ]);

  const handleSendMessage = (content: string) => {
    const userMessage: Message = {
      id: Date.now().toString(),
      content,
      isAi: false,
      timestamp: new Date().toLocaleTimeString("ko-KR", {
        hour: "2-digit",
        minute: "2-digit",
      }),
    };

    setMessages((prev) => [...prev, userMessage]);

    // AI 응답 시뮬레이션
    setTimeout(() => {
      const aiMessage: Message = {
        id: (Date.now() + 1).toString(),
        content:
          "네, 도움이 되셨다니 기쁩니다! 추가로 궁금하신 점이 있으시면 언제든지 물어보세요.",
        isAi: true,
        timestamp: new Date().toLocaleTimeString("ko-KR", {
          hour: "2-digit",
          minute: "2-digit",
        }),
      };
      setMessages((prev) => [...prev, aiMessage]);
    }, 1000);
  };

  return (
    <div
      className={`flex h-screen ${
        theme === "dark" ? "bg-gray-950" : "bg-gray-50"
      }`}
    >
      <ChatList
        chats={chats}
        selectedChatId={selectedChatId}
        onSelectChat={setSelectedChatId}
        isOpen={isSidebarOpen}
        theme={theme}
        accentColor={accentColor}
      />
      <ChatWindow
        messages={messages}
        onSendMessage={handleSendMessage}
        onToggleSidebar={() => setIsSidebarOpen(!isSidebarOpen)}
        theme={theme}
        accentColor={accentColor}
        onThemeChange={setTheme}
        onAccentColorChange={setAccentColor}
      />
    </div>
  );
}
