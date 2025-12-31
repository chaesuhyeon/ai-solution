import React, { useState } from 'react';
import { Bot, User, Copy, Check } from 'lucide-react';
import { Message, Theme, AccentColor } from '../App';

interface MessageBubbleProps {
  message: Message;
  theme: Theme;
  accentColor: AccentColor;
}

const accentColors = {
  blue: 'bg-blue-600',
  purple: 'bg-purple-600',
  green: 'bg-green-600',
  pink: 'bg-pink-600',
  orange: 'bg-orange-600',
};

export function MessageBubble({ message, theme, accentColor }: MessageBubbleProps) {
  const [copied, setCopied] = useState(false);

  const handleCopy = () => {
    navigator.clipboard.writeText(message.content);
    setCopied(true);
    setTimeout(() => setCopied(false), 2000);
  };

  const renderContent = (content: string) => {
    const parts = content.split(/(```[\s\S]*?```)/g);
    
    return parts.map((part, index) => {
      if (part.startsWith('```') && part.endsWith('```')) {
        const code = part.slice(3, -3);
        const lines = code.split('\n');
        const language = lines[0].trim();
        const codeContent = lines.slice(1).join('\n');
        
        return (
          <div key={index} className="my-3 rounded-lg overflow-hidden bg-gray-950 border border-gray-800">
            <div className="flex items-center justify-between px-4 py-2 bg-gray-900 border-b border-gray-800">
              <span className="text-sm text-gray-400">{language || 'code'}</span>
              <button
                onClick={() => navigator.clipboard.writeText(codeContent)}
                className="text-gray-400 hover:text-white transition-colors p-1 rounded hover:bg-gray-800"
              >
                <Copy className="w-4 h-4" />
              </button>
            </div>
            <pre className="p-4 overflow-x-auto">
              <code className="text-sm text-gray-100 font-mono">{codeContent}</code>
            </pre>
          </div>
        );
      }
      return <span key={index}>{part}</span>;
    });
  };

  return (
    <div className={`flex gap-3 ${message.isAi ? '' : 'flex-row-reverse'}`}>
      {/* Avatar */}
      <div
        className={`w-9 h-9 rounded-full flex items-center justify-center flex-shrink-0 ${
          message.isAi 
            ? theme === 'dark'
              ? 'bg-gray-800 text-gray-300'
              : 'bg-gray-200 text-gray-700'
            : `${accentColors[accentColor]} text-white`
        }`}
      >
        {message.isAi ? (
          <Bot className="w-5 h-5" />
        ) : (
          <User className="w-4 h-4" />
        )}
      </div>

      {/* Message Content */}
      <div className={`flex-1 ${message.isAi ? '' : 'flex flex-col items-end'}`}>
        <div className="relative group">
          <div
            className={`inline-block max-w-2xl px-4 py-3 rounded-2xl ${
              message.isAi
                ? theme === 'dark'
                  ? 'bg-gray-800 text-gray-100 border border-gray-700'
                  : 'bg-white text-gray-900 border border-gray-200 shadow-sm'
                : `${accentColors[accentColor]} text-white`
            }`}
          >
            <div className="whitespace-pre-wrap">{renderContent(message.content)}</div>
          </div>
          
          {/* Copy Button for AI messages */}
          {message.isAi && (
            <button
              onClick={handleCopy}
              className={`absolute top-2 right-2 p-1.5 rounded-md transition-all opacity-0 group-hover:opacity-100 ${
                theme === 'dark'
                  ? 'bg-gray-700 hover:bg-gray-600 text-gray-300'
                  : 'bg-gray-100 hover:bg-gray-200 text-gray-600'
              }`}
              title="복사"
            >
              {copied ? (
                <Check className="w-3.5 h-3.5" />
              ) : (
                <Copy className="w-3.5 h-3.5" />
              )}
            </button>
          )}
        </div>
        <span className={`text-xs mt-1 ${
          theme === 'dark' ? 'text-gray-500' : 'text-gray-500'
        }`}>
          {message.timestamp}
        </span>
      </div>
    </div>
  );
}
